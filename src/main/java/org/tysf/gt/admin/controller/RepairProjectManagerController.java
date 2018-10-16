package org.tysf.gt.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 维修项目管理
 * @author wuzhe
 *
 */
import org.springframework.web.servlet.ModelAndView;
import org.tysf.gt.pojo.RepairArea;
import org.tysf.gt.pojo.RepairProject;
import org.tysf.gt.service.IRepairAreaService;
import org.tysf.gt.service.IRepairProjectService;
import org.tysf.gt.utils.GsonUtils;

@Controller
@RequestMapping("/")

public class RepairProjectManagerController {
	@Resource
	private IRepairProjectService repairProjectService;
	@Resource
	private IRepairAreaService repairAreaService;

	@RequestMapping("admin/repairManager.do")
	public ModelAndView initRepairManager() {
		ModelAndView mv = new ModelAndView();
		List<RepairProject> repairProjectList = repairProjectService.queryRepairProject();
		List<RepairArea> repairAreaList = repairAreaService.queryRepairArea();
		mv.addObject("repairProjectList", repairProjectList);
		mv.addObject("repairAreaList", repairAreaList);
		mv.setViewName("admin/RepairProjectManager");
		return mv;
	}
    //根据报修区域ID查找报修项目
	@RequestMapping("/queryRepairProjectByRaid.do")
	public void queryRepairProjectByRaid(int raid, HttpServletResponse response) {
		List<RepairProject> repairProjectList = repairProjectService.queryRepairProjectByRaid(raid);
		String json = GsonUtils.getJsonStr(repairProjectList);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
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
	//添加报修项目
	@RequestMapping("admin/repariProjectAdd.do")
	public void repairProjectAddGetRpid(String rpname,int raid,HttpServletResponse response){
		RepairArea area=new RepairArea();
		area.setRaid(raid);
		RepairProject repairProject=new RepairProject(rpname,area);
		int rpid=repairProjectService.repairProjectAddGetRpid(repairProject);
		PrintWriter writer=null;
		try {
			writer=response.getWriter();
			writer.write(rpid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
	//修改报修项目
	@RequestMapping("admin/repairProjectModify.do")
	public String repairProjectModify(String rpid,String repairArea,String rpname){
		System.out.println(rpid+repairArea+rpname);
		RepairArea area=new RepairArea();
		area.setRaid(Integer.parseInt(repairArea));
		RepairProject repairProject=new RepairProject(Integer.parseInt(rpid), rpname, area);
		repairProjectService.repairProjectModify(repairProject);
		return  "redirect:/admin/repairManager.do";
	}
	//查询报修项目
	@RequestMapping("repairProjectByRmidForJson.do")
	public void repairProjectByRmidForJson(String rmid,HttpServletResponse response){
		List<RepairProject> repairProjectList = repairProjectService.queryRepairProjectByRaid(1);
		String json = GsonUtils.getJsonStr(repairProjectList);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
	@RequestMapping("repairProjectForJson.do")
	public void repairProjectForJson(HttpServletResponse response){
		List<RepairProject> repairProjectList = repairProjectService.queryRepairProject();
		String json = GsonUtils.getJsonStr(repairProjectList);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
