package org.tysf.gt.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 显示图片
 * 
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("admin")
public class ShowImageController {
	/**
	 * 图片显示类
	 * 
	 * @param id
	 */
	@RequestMapping("showImage.do")
	public void showImage(String id, HttpSession session, HttpServletResponse response) {
		String path = session.getServletContext().getRealPath("onlineRepairImage");
		File file = new File(path, id);
		response.setCharacterEncoding("utf-8");
		response.setContentType("image/*");
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = response.getOutputStream();
			int length = 0;
			byte[] b = new byte[1024];
			while ((length = in.read(b)) > 0) {
				out.write(b, 0, length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
