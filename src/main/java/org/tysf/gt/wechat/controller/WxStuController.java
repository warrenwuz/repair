package org.tysf.gt.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tysf.gt.pojo.RepairLog;
import org.tysf.gt.pojo.RepairProject;
import org.tysf.gt.pojo.StudentMsg;
import org.tysf.gt.pojo.WxOnlineRepairMsg;
import org.tysf.gt.service.IRepairLogService;
import org.tysf.gt.service.IStuMsgService;
import org.tysf.gt.service.IWxOnlineRepairMsgService;
import org.tysf.gt.service.impl.WebSocketTest;
import org.tysf.gt.utils.UUIDUtils;

/**
 * 微信用户
 * 
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("wx")
public class WxStuController {
	@Resource
	private IStuMsgService stuMsgService;
	@Resource
	private IWxOnlineRepairMsgService wxOnlineRepairMsgService;
	@Resource
	private IRepairLogService repairLogService;//维修日志

	// 初始化绑定页面
	@RequestMapping(value = "/bindWxSystem.do", method = RequestMethod.GET)
	public ModelAndView initWxbind(String openid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("openid", openid);
		String stuid = stuMsgService.queryStuidByopenid(openid);
		if (stuid != null) {// 证明用户已经成功绑定了微信公众号
			mv.setViewName("redirect:/static/wxbinderror.html");
		} else {
		  mv.setViewName("wx/bindWxSystem");
		}
		return mv;
	}

	// 绑定微信公众号
	@RequestMapping(value="/bindWxSystem.do",method=RequestMethod.POST)                                                                             
	public void bindWxSystem(StudentMsg studentMsg, HttpServletResponse response) {
		PrintWriter writer = null;
		System.out.println(studentMsg);
		StudentMsg student = stuMsgService.bindWxSystem(studentMsg);// 查找学生信息
		try {
			writer = response.getWriter();
			if (student != null && "".equals(student.getOpenid())) {// 验证成功并且没有绑定微信号
				stuMsgService.updateOpenidByStuid(studentMsg);
				writer.write("0");
			} else {
				if (student == null) {
					writer.write("1");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}
	// 更新用户绑定
	@RequestMapping("updateWxSystem.do")
	public void updateWxSystem(StudentMsg studentMsg, HttpServletResponse response) {
		stuMsgService.updateOpenidByStuid(studentMsg);// 覆盖原来的绑定信息
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("1");// 表示更新成功
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	// 初始化在线报修
	@RequestMapping(value = "/wxOnlineRepair.do", method = RequestMethod.GET)
	public ModelAndView initOnlineRepair(String openid) {
		StudentMsg studentMsg = stuMsgService.queryStudentMsgByOpenid(openid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("studentMsg", studentMsg);
		mv.setViewName("wx/wxOnlineRepair");
		return mv;
	}

	// 提交在线报修单
	@RequestMapping(value = "/wxOnlineRepair.do", method = RequestMethod.POST)
	public void onlineRepair(String uploadImages[], String stuid, String tel, String rpid, String projectdetail,
			HttpServletResponse response) {
		WxOnlineRepairMsg wxOnlineRepairMsg = new WxOnlineRepairMsg();
		String wpid = UUIDUtils.getUUID();// 设置报修单UUID
		wxOnlineRepairMsg.setWpid(wpid);
		wxOnlineRepairMsg.setStudentMsg(new StudentMsg(stuid));
		wxOnlineRepairMsg.setTel(tel);
		wxOnlineRepairMsg.setRepairProject(new RepairProject(Integer.parseInt(rpid)));
		wxOnlineRepairMsg.setProjectdetail(projectdetail);
		wxOnlineRepairMsg.setTimestamp(new Timestamp(System.currentTimeMillis()));
		wxOnlineRepairMsg.setStatus(0);
		wxOnlineRepairMsgService.addWxOnlineRepairMsg(wxOnlineRepairMsg);
		wxOnlineRepairMsg=wxOnlineRepairMsgService.queryrepairMsgDetailByWpid(wpid);
		//增加日志记录
		repairLogService.addRepairLog(new RepairLog("报修人【"+wxOnlineRepairMsg.getStudentMsg().getSname()+"】提交报修", wpid));
		if (uploadImages.length != 0) {
			@SuppressWarnings("unchecked")
			Map<String, Object> params = new HashedMap();
			params.put("uploadImages", uploadImages);
			params.put("wpid", wpid);
			params.put("imagetype", 0);
			wxOnlineRepairMsgService.addSImagesByWpid(params);
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("1");
			WebSocketTest.sendMsg(wpid);// WebSocket发送通知
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
	/**
	 * 查看维修进度
	 * @param wpid
	 * @return
	 */
	@RequestMapping("repairProgress.do")
	public ModelAndView repairProgress(String wpid){
		ModelAndView mv=new ModelAndView();
		List<RepairLog>repairLogList = repairLogService.queryRepairLogByWpid(wpid);
		mv.addObject("repairLogList", repairLogList);
		mv.setViewName("wx/repair_progress");
		return mv;
	}
}
