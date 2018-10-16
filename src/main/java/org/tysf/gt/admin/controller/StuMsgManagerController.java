package org.tysf.gt.admin.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tysf.gt.pojo.Flat;
import org.tysf.gt.pojo.StudentMsg;
import org.tysf.gt.service.IStuMsgService;
import org.tysf.gt.utils.HanyuPinyinHelper;
import org.tysf.gt.utils.MD5Utils;
/**
 * 学生信息管理
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("admin/")
public class StuMsgManagerController {
	@Resource
	private IStuMsgService stuMsgService;
	@RequestMapping(value="stuMsgManager.do",method=RequestMethod.GET)
	//管理学生主页面
	public ModelAndView initstuMsgManager() throws IOException {
		ModelAndView mv = new ModelAndView();
		List<StudentMsg> stuMsgList = stuMsgService.queryStudentMsg();
		mv.addObject("stuMsgList", stuMsgList);
		mv.setViewName("admin/stuMsgManager");
		return mv;
	}
	@RequestMapping(value="addStuMsg.do",method=RequestMethod.POST)
	//添加学生信息
	public String addstuMsgManager(@RequestParam Map<String,String>param){
		StudentMsg studentMsg=new StudentMsg();
		studentMsg.setStuid(param.get("stuid"));
		studentMsg.setSname(param.get("sname"));
		studentMsg.setFlat(new Flat(Integer.parseInt(param.get("fid"))));
		studentMsg.setDormitory(param.get("dormitory"));
		studentMsg.setTel(param.get("tel"));
		String password=HanyuPinyinHelper.getFirstLettersUp(param.get("sname"))+param.get("stuid");
		System.out.println("password="+password);
		password=MD5Utils.md5Password(password);
		studentMsg.setPassword(password);
		stuMsgService.addstuMsgManager(studentMsg);
		return "redirect:/admin/stuMsgManager.do";
	}
	
	//修改学生信息
	@RequestMapping(value="modifyStuMsg.do",method=RequestMethod.POST)
    public void modifyStuMsg(@RequestParam Map<String,String>param,HttpServletResponse response){
		StudentMsg studentMsg=new StudentMsg();
		studentMsg.setStuid(param.get("stuid"));
		studentMsg.setFlat(new Flat(Integer.parseInt(param.get("fid"))));
		studentMsg.setDormitory(param.get("dormitory"));
		studentMsg.setTel(param.get("tel"));
		stuMsgService.modifyStuMsg(studentMsg);
		PrintWriter writer =null;
		 try {
			writer = response.getWriter();
			writer.write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
}
