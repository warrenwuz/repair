package org.tysf.gt.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tysf.gt.dao.IRepairLogDao;
import org.tysf.gt.pojo.RepairLog;
import org.tysf.gt.pojo.Repairman;
import org.tysf.gt.pojo.WxOnlineRepairMsg;
import org.tysf.gt.service.IRepairmanService;
import org.tysf.gt.service.IWxOnlineRepairMsgService;
import org.tysf.gt.utils.MD5Utils;

/**
 * 微信维修人员登录
 * 
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("wx")
public class WxRepairmanController {
	@Resource
	private IRepairmanService repairmanService;
	@Resource
	private IWxOnlineRepairMsgService wxOnlineRepairMsgService;
	@Resource
	private IRepairLogDao repairLogService;//维修日志
	// 初始化绑定页面
	@RequestMapping(value = "/bindWxRepairmanSystem.do", method = RequestMethod.GET)
	public ModelAndView initWxbind(String openid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("openid", openid);
		String rmid = repairmanService.queryRmidByopenid(openid);
		if (rmid != null) {// 证明用户已经成功绑定了微信公众号
			mv.setViewName("redirect:/static/wxbinderror.html");
		} else {
			mv.setViewName("wx/bindWxSystem");
		}
		return mv;
	}
	// 绑定微信公众号
		@RequestMapping(value="/bindWxRepairmanSystem.do",method=RequestMethod.POST)                                                                             
		public void bindWxSystem(Repairman repairman, HttpServletResponse response) {
			System.out.println(repairman);
			PrintWriter writer = null;
			repairman.setRmpassword(MD5Utils.md5Password(repairman.getRmpassword()));
			Repairman repair = repairmanService.bindWxRepairmanSystem(repairman);// 查找维修工人信息
			System.out.println(repair);
			try {
				writer = response.getWriter();
				if (repair != null && "".equals(repair.getOpenid())) {// 验证成功并且没有绑定微信号
					repairmanService.updateOpenidByRmid(repairman);
					writer.write("0");
				} else {
				
					if (repair == null) {
						writer.write("1");
					
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		}
		// 更新用户绑定
		@RequestMapping("updateWxRepairmanSystem.do")
		public void updateWxSystem(Repairman repairman, HttpServletResponse response) {
			repairmanService.updateOpenidByRmid(repairman);// 覆盖原来的绑定信息
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
		/**
		 * 维修工人微信维修受理界面
		 * 
		 * @return
		 */
		@RequestMapping("repairmanAcceptance.do")
		public ModelAndView repairmanAcceptance(String wpid) {
			WxOnlineRepairMsg wxOnlineRepairMsg = wxOnlineRepairMsgService.queryrepairMsgDetailByWpid(wpid);
			ModelAndView mv = new ModelAndView();
			mv.addObject("WxOnlineRepairMsg", wxOnlineRepairMsg);
			mv.setViewName("repairman/repairmanAcceptance");
			return mv;

		}

		/***
		 * 维修师傅点击受理维修
		 */
		@RequestMapping("repairAcceptanceWxOnlineMsg.do")
		public void repairAcceptanceWxOnlineMsg(String wpid, HttpServletResponse response) {
			wxOnlineRepairMsgService.updateStatusByWpid(wpid);
			WxOnlineRepairMsg wxOnlineRepairMsg=wxOnlineRepairMsgService.queryrepairMsgDetailByWpid(wpid);
			//日志记录
			repairLogService.addRepairLog(new RepairLog("维修师傅【"+wxOnlineRepairMsg.getRepairman().getRmname()+"】受理维修单", wxOnlineRepairMsg.getWpid()));
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.write("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				writer.close();
			}

		}
	    /**
	     * 微信师傅提交完工
	     * @param wpid
	     * @param uploadImages
	     * @param completeremark
	     */
		@RequestMapping(value = "CompleteWxOnlineRepair.do", method = RequestMethod.POST)
		public void completeWxOnlineRepair(String wpid, String uploadImages[], String completeremark,HttpServletResponse response) {
			WxOnlineRepairMsg wxOnlineRepairMsg=new WxOnlineRepairMsg();
			wxOnlineRepairMsg.setWpid(wpid);
			wxOnlineRepairMsg.setCompleteremark(completeremark);
			wxOnlineRepairMsgService.addCompleteRemarkAndStatusByWpid(wxOnlineRepairMsg);
			wxOnlineRepairMsg=wxOnlineRepairMsgService.queryrepairMsgDetailByWpid(wpid);
			//日志记录
			repairLogService.addRepairLog(new RepairLog("维修师傅【"+wxOnlineRepairMsg.getRepairman().getRmname()+"】提交完工", wxOnlineRepairMsg.getWpid()));
			if (uploadImages.length != 0) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("uploadImages", uploadImages);
				params.put("wpid", wpid);
				params.put("imagetype", 1);
				wxOnlineRepairMsgService.addSImagesByWpid(params);
			}
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.write("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				writer.close();
			}
		}
		/***
		 * 显示提交完工页面
		 * 
		 * @param wpid
		 * @return
		 */
		@RequestMapping(value = "CompleteWxOnlineRepair.do", method = RequestMethod.GET)
		public ModelAndView completeWxOnlineRepair(String wpid) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("wpid", wpid).setViewName("repairman/completeWxOnlineRepair");
			return mv;
		}
		/***
		 * 初始化维修师傅维修记录查询
		 * 
		 * @param openid
		 * @param pageNumber
		 * @param pageSize
		 * @return
		 */
		@RequestMapping(value = "repairmanRecode.do", method = RequestMethod.GET)
		public ModelAndView repairmanRecode(String openid,HttpSession session) {
			ModelAndView mv = new ModelAndView();
			session.setAttribute("openid", openid);
			mv.setViewName("repairman/RepairmanRecord");
			return mv;
		}
}
