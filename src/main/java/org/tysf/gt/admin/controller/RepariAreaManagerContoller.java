package org.tysf.gt.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tysf.gt.pojo.RepairArea;
import org.tysf.gt.service.IRepairAreaService;
import org.tysf.gt.utils.GsonUtils;

@Controller
@RequestMapping("/")
public class RepariAreaManagerContoller {
	@Resource
	private IRepairAreaService repairAreaService;

	@RequestMapping("admin/repariAreaAdd.do")
	public void addRepairArea(String raname, HttpServletResponse response) {
		int raid = repairAreaService.addRepairAreaGetRaid(raname);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(raid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}

	@RequestMapping("admin/repariAreaModify.do")
	public String modifyRepairArea(int raid, String raname, HttpServletResponse response) {
		RepairArea repairArea = new RepairArea(raid, raname);
		repairAreaService.modifyRepairArea(repairArea);
		return "redirect:/admin/repairManager.do";
	}

	/**
	 * 查找报修区域 并将报修区域转换成Json格式传到前台
	 */
	@RequestMapping("queryRepairAreaJson.do")
	public void queryRepairAreaJson(HttpServletResponse response) {
		List<RepairArea> repairAreaList = repairAreaService.queryRepairArea();
		String json = GsonUtils.getJsonStr(repairAreaList);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}

}
