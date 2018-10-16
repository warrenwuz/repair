package org.tysf.gt.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tysf.gt.pojo.Administrator;
import org.tysf.gt.pojo.RepairLog;
import org.tysf.gt.pojo.Repairman;
import org.tysf.gt.pojo.WxOnlineRepairMsg;
import org.tysf.gt.service.IRepairLogService;
import org.tysf.gt.service.IWxOnlineRepairMsgService;
import org.tysf.gt.thread.SmsNoticeThread;
import org.tysf.gt.utils.GsonUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 在线保修后台管理类
 * 
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("/admin")
public class WxOnLineRepairMsgManagerContoller {
	@Resource
	private IWxOnlineRepairMsgService wxOnlineRepairMsgService;
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;// Spring 线程池
	@Resource
	private SmsNoticeThread smsNoticeThread;// 发送消息的线程
	@Resource
	private IRepairLogService repairLogService;//维修日志
    /**
     * 初始化查询页面
     * @param session
     * @param status
     * @return
     */
	@RequestMapping(value="wxOnLineRepairMsgManager.do",method=RequestMethod.GET)
	public ModelAndView queryWxOnLineRepairMsg(HttpSession session,@RequestParam(defaultValue = "0") int status) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/WxOnlineRepairMsgManager");
		mv.addObject("status", status);
		return mv;
	}
    /**
     * 
     * @param pageNumber  分页页数
     * @param pageSize  分页大小
     * @param status   维修状态
     * @param response 响应json数据
     * @throws InterruptedException
     */
	@RequestMapping("queryWxOnileRepairMsg.do")
	public void queryCountUnCheck(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") int status,
			HttpServletResponse response) throws InterruptedException {
		PageHelper.startPage(pageNumber, pageSize);
		List<WxOnlineRepairMsg> WxOnlineRepairMsg = wxOnlineRepairMsgService.queryWxOnlineRepairMsg(status);
		PageInfo<WxOnlineRepairMsg> page = new PageInfo<WxOnlineRepairMsg>(WxOnlineRepairMsg);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", page.getTotal());
		data.put("rows", WxOnlineRepairMsg);
		String msg = GsonUtils.getJsonStr(data);
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	/**
	 * 报修单详情
	 * @param wpid 通过报修单编号查找报修单详情      
	 * @return
	 */
	@RequestMapping("repairMsgDetail.do")
	public ModelAndView repairMsgDetailManager(String wpid, HttpServletRequest request) {
		WxOnlineRepairMsg wxOnlineRepairMsg = wxOnlineRepairMsgService.queryrepairMsgDetailByWpid(wpid);
		System.out.println(wxOnlineRepairMsg);
		ModelAndView mv = new ModelAndView();
		mv.addObject("WxOnlineRepairMsg", wxOnlineRepairMsg);
		mv.setViewName("admin/RepairMsgDetail");
		return mv;
	}

	/**
	 * 
	 * @param param  派工类型
	 * @param response
	 */
	@RequestMapping("dispatchingRepairMsg.do")
	public void dispatchingRepairMsg(@RequestParam Map<String, Object> param, HttpServletResponse response,HttpSession session) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			int dispatchingType = Integer.parseInt((String) param.get("dispatchingType"));
			if (dispatchingType == 0) {// 系统自动派工(利用mysql数据库随机返回一组数据,效率还没有解决)
				String rmid = wxOnlineRepairMsgService.queryRepairmanForFree();// 寻找空闲的维修工(处理的维修单小于15)
				param.put("rmid", rmid);// 设置维修工
				if ("".equals(rmid) || rmid == null) {// 目前没有空闲维修工
					System.out.println("执行" + rmid);
					writer.write("0");
					return;
				}
			}
			WxOnlineRepairMsg wxOnlineRepairMsg = new WxOnlineRepairMsg();
			wxOnlineRepairMsg.setWpid((String) param.get("wpid"));
			wxOnlineRepairMsg.setRepairman(new Repairman((String) param.get("rmid")));
			wxOnlineRepairMsg.setRemark((String) param.get("remark"));
			wxOnlineRepairMsg.setDispatchingType(dispatchingType);
			wxOnlineRepairMsg.setArrangeAdmin(new Administrator((String)session.getAttribute("admin")));
			wxOnlineRepairMsgService.dispatchWxOnlineRepairMsg(wxOnlineRepairMsg);
			wxOnlineRepairMsg=wxOnlineRepairMsgService.queryrepairMsgDetailByWpid((String) param.get("wpid"));
			//日志记录
			repairLogService.addRepairLog(new RepairLog("管理员【"+wxOnlineRepairMsg.getArrangeAdmin().getAdminname()+"】进行派工", wxOnlineRepairMsg.getWpid()));

			/**
			 * 1.向维修师傅绑定的微信号发送消息, 2.查询需要发送的相关消息 3.发送模板消息 4.维修师傅如果没有点击,则发送短信通知
			 */
			smsNoticeThread.setWpid((String) param.get("wpid"));
			taskExecutor.execute(smsNoticeThread);
			writer.write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}	

	/**
	 * 管理员验收订单
	 * @param wpid
	 * @return
	 */
	@RequestMapping("checkandacceptance.do")
	public String checkAndAcceptance(String wpid){
		wxOnlineRepairMsgService.checkAndAcceptance(wpid);
		WxOnlineRepairMsg	wxOnlineRepairMsg=wxOnlineRepairMsgService.queryrepairMsgDetailByWpid(wpid);
		//日志记录
		repairLogService.addRepairLog(new RepairLog("管理员【"+wxOnlineRepairMsg.getArrangeAdmin().getAdminname()+"】检查完工", wxOnlineRepairMsg.getWpid()));
		return "redirect:/admin/repairMsgDetail.do?wpid="+wpid;
	}
}
