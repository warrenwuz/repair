package org.tysf.gt.admin.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tysf.gt.pojo.Flat;
import org.tysf.gt.service.IFlatService;
import org.tysf.gt.utils.GsonUtils;
/**
 * 公寓信息管理
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("admin/")
public class FlatController {
	@Resource
	private IFlatService flatService;
	@RequestMapping("showFlatMsg.do")
	public ModelAndView queryFlat(){
		ModelAndView mv=new ModelAndView();
		List<Flat> list = flatService.queryFlatAll();
		mv.addObject("list", list);
		mv.setViewName("admin/flatMsgManager");
		return mv;
	}
	@RequestMapping("addFlat.do")
	public String addFlat(String fid,String fname){
		Flat flat=new Flat(Integer.parseInt(fid), fname);
		flatService.addFlat(flat);
		return "redirect:/admin/showFlatMsg.do";
		
	}
	@RequestMapping("modifyFlat.do")
	public String modifyFlat(String fid,String fname){
		System.out.println(fid+fname);
		Flat flat=new Flat(Integer.parseInt(fid), fname);
		flatService.modifyFlat(flat);
		return "redirect:/admin/showFlatMsg.do";
	}
	@RequestMapping("queryAllFlatJson")
   public void queryFlatJson(HttpServletResponse response){
	   List<Flat> list = flatService.queryFlatAll();
	   String json = GsonUtils.getJsonStr(list);
	   PrintWriter writer=null;
	   try {
		   response.setCharacterEncoding("utf-8");
		   writer= response.getWriter();
		   writer.println(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		
	}
	   
   }
}
