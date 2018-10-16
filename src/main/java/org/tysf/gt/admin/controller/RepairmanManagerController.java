package org.tysf.gt.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tysf.gt.pojo.RepairProject;
import org.tysf.gt.pojo.Repairman;
import org.tysf.gt.service.IRepairmanService;
import org.tysf.gt.utils.GsonUtils;
import org.tysf.gt.utils.HanyuPinyinHelper;
import org.tysf.gt.utils.MD5Utils;
import org.tysf.gt.utils.RepairmanIdentityUtils;

@Controller
@RequestMapping("admin")
public class RepairmanManagerController {
	@Resource
	private IRepairmanService repairmanService;

	@RequestMapping("RepairmanManager.do")
	public String RepairmanManager() {
		return "admin/RepairmanManager";
	}

	/***
	 * 查询所有维修工人信息
	 * 
	 * @param response
	 */
	@RequestMapping("queryRepairmaForJson.do")
	public void queryRepairmaForJson(HttpServletResponse response) {
		List<Repairman> reparimans = repairmanService.queryRepariman();
		PrintWriter writer = null;
		String json = GsonUtils.getJsonStr(reparimans);
		response.setCharacterEncoding("utf-8");
		try {
			writer = response.getWriter();
			writer.write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	@RequestMapping("addRepairman.do")
	public void addRepairman(Integer count,Repairman repairman,String[] rpids,HttpServletResponse response) throws IOException {
		String rmid=RepairmanIdentityUtils.getRepairmaIdentify(count);
		repairman.setRmid(rmid);
		// 密码取管理人员首字母大写  在实体类获的密码的时候对密码进行了加密
		String rmpassword = HanyuPinyinHelper.getFirstLettersUp(repairman.getRmname()) + rmid;
		rmpassword=MD5Utils.md5Password(rmpassword);
		repairman.setRmpassword(rmpassword);
		repairmanService.addRepairman(repairman);
		Map<String, Object>params=new HashMap<String,Object>();
		params.put("rmid", rmid);
		for(String rpid:rpids){
			params.put("rpid", rpid);
			repairmanService.addRepairProjectByRmid(params);
		}
		PrintWriter writer = null;
		try{
			writer = response.getWriter();
			writer.write(rmid);
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
	@RequestMapping("queryProjectByRmidForJson.do")
	public void queryProjectByRmidForJson(String rmid,HttpServletResponse response){
	List<RepairProject> repairProjects=	repairmanService.queryProjectByRmid(rmid);
	response.setCharacterEncoding("utf-8");
	PrintWriter writer=null;
	try {
		writer=response.getWriter();
		String json=GsonUtils.getJsonStr(repairProjects);
		writer.write(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		writer.close();
	}
	}
	@RequestMapping("queryUnSelectRepairProjectForJson.do")
	public void queryUnSelectRepairProject(String rmid,HttpServletResponse response){
		List<RepairProject> repairProjects=	repairmanService.queryUnSelectRepairProject(rmid);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer=null;
		try {
			writer=response.getWriter();
			String json=GsonUtils.getJsonStr(repairProjects);
			writer.write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			writer.close();
		}
	}
	@RequestMapping("addRepairProjectForRepairman.do")
	public void addRepairProjectForRepairman(String[] rpids,String rmid,HttpServletResponse response){
		Map<String, Object>params=new HashMap<String,Object>();
		params.put("rmid", rmid);
		for(String rpid:rpids){
			params.put("rpid", rpid);
			repairmanService.addRepairProjectByRmid(params);
		}
	}
	/***
    * 修改维修人员信息
    * @param administrator  更新的管理员信息
    * @param fidld          需要更新的管理员信息字段
    * @param response
    */
	@RequestMapping("modifyRepairman.do")
	public void modifyRepairman(Repairman repairman, String field, HttpServletResponse response) {
		System.out.println(repairman);
		System.out.println(field);
		//首先需要判断更新的是哪个字段
		if ("rmname".equals(field)) {
			// 密码取维修人员首字母大写
			String rmpassword = HanyuPinyinHelper.getFirstLettersUp(repairman.getRmname())
					+ repairman.getRmid();
			rmpassword = MD5Utils.md5Password(rmpassword);
			repairman.setRmpassword(rmpassword);
			repairmanService.modifyRepairmanNameAndPassword(repairman);
		} else {
			repairmanService.modifyRepairmanTel(repairman);
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
