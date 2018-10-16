package org.tysf.gt.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tysf.gt.pojo.Administrator;
import org.tysf.gt.service.IAdministratoraService;
import org.tysf.gt.utils.AdminIdentifyUtils;
import org.tysf.gt.utils.GsonUtils;
import org.tysf.gt.utils.HanyuPinyinHelper;
import org.tysf.gt.utils.MD5Utils;

/**
 * 管理员管理
 * 
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("/")
public class AdminManager {
	@Resource
	private IAdministratoraService administratoraService;
    /**
     * 管理员登陆
     * @param administrator
     * @param session
     * @return
     */
	@RequestMapping("Login.do")
	public ModelAndView adminLogin(Administrator administrator, HttpSession session) {
		administrator.setAdminpassword(MD5Utils.md5Password(administrator.getAdminpassword()));//转换用户名密码为MD5
		Administrator admin = administratoraService.queryAdminByIdAndPassword(administrator);
		ModelAndView mv = new ModelAndView();
		if (admin != null) {
			session.setAttribute("admin", admin.getAdminid());
			session.setAttribute("adminName", admin.getAdminname());
			mv.setViewName("admin/WxOnlineRepairMsgManager");
			return mv;
		}
		mv.addObject("errorMsg", "工号或密码错误");
		mv.setViewName("forward:/index.jsp");
		return mv;
	}

	// 管理员退出
	@RequestMapping("admin/adminLoginOut.do")
	public String adminLoginOut(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/index.jsp";
	}

	// 初始化管理员管理页面
	@RequestMapping("admin/adminManager.do")
	public String adminManager() {
		return "admin/AdminMsgManager";
	}

	@RequestMapping("admin/queryAdminGetJson.do")
	public void queryAdminGetJson(HttpServletResponse response) {
		List<Administrator> administrators = administratoraService.queryAdmin();
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(GsonUtils.getJsonStr(administrators));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
     /***
      * 
      * @param administrator
      * @param count     所有管理人员个数
      * @param response
      */
	@RequestMapping("admin/addAdministrator.do")
	public void addAdministrator(Administrator administrator, Integer count, HttpServletResponse response) {
		String adminid = AdminIdentifyUtils.getAdminIdentify(count);
		administrator.setAdminid(adminid);
		// 密码取管理人员首字母大写
		String adminpassword = HanyuPinyinHelper.getFirstLettersUp(administrator.getAdminname()) + adminid;
		adminpassword = MD5Utils.md5Password(adminpassword);
		administrator.setAdminpassword(adminpassword);
		administratoraService.addAdministrator(administrator);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(adminid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}
    /***
     * 修改管理员信息
     * @param administrator  更新的管理员信息
     * @param fidld          需要更新的管理员信息字段
     * @param response
     */
	@RequestMapping("admin/modifyAdministrator.do")
	public void modifyAdministrator(Administrator administrator, String field, HttpServletResponse response) {
		System.out.println(administrator);
		System.out.println(field);
		//首先需要判断更新的是哪个字段
		if ("adminname".equals(field)) {
			// 密码取管理人员首字母大写
			String adminpassword = HanyuPinyinHelper.getFirstLettersUp(administrator.getAdminname())
					+ administrator.getAdminid();
			adminpassword = MD5Utils.md5Password(adminpassword);
			administrator.setAdminpassword(adminpassword);
			administratoraService.modifyAdministratorNameAndPassword(administrator);
		} else {
			administratoraService.modifyAdministratorTel(administrator);
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
}
