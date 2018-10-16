package org.tysf.gt.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.tysf.gt.pojo.SImage;
import org.tysf.gt.service.ISimageService;
import org.tysf.gt.utils.GsonUtils;
import org.tysf.gt.utils.UUIDUtils;

/**
 * 上传图片接口
 * 
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("upload")
public class UploadController {
	@Resource
	private ISimageService simageService;
	/**
	 * 上传图片(这里需要注意的是MultipartFile的名字必须与fileVal的名字一致)
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("uploadImage.do")
	public void uploadImage(MultipartFile image, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		PrintWriter out = null;
			HttpSession session = request.getSession();
			// 获取容器路径
			String filePath = session.getServletContext().getRealPath("/onlineRepairImage");
			File parent=new File(filePath);
			if(!parent.exists()){
				parent.mkdir();
			}
			// 获取文件姓名
			String fileName = image.getOriginalFilename();
			// 去除后缀名名字
			String realFileName = fileName.substring(0, fileName.lastIndexOf("."));
			// 建立一个附件的唯一标识
			String simageid = UUIDUtils.getUUID();
			// 获得保存路径的文件名
			String savefileName = fileName.replace(realFileName, simageid);
			// 建立文件
			File filedest = new File(parent, savefileName);
			// 上传到本地
			image.transferTo(filedest);
			// 建立时间戳
		     Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SImage simage=new SImage(simageid, savefileName, timestamp);
	     	simageService.addSImage(simage);
			out = response.getWriter();
			out.write(GsonUtils.getJsonStr(simageid));//使用weui.js 的upload返回值必须采用json形式
		

	}
}
