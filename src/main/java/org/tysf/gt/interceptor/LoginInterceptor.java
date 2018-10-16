package org.tysf.gt.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tysf.gt.service.IWxOnlineRepairMsgService;
/**
 * 登陆拦截器
 * @author wuzhe
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private IWxOnlineRepairMsgService wxOnlineRepairMsgService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		String admin = (String)session.getAttribute("admin");
		if(admin==null||"".equals(admin)){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return false;
		}
		/**
		 * 利用拦截每次对未读消息进行渲染
		 */
		int count=wxOnlineRepairMsgService.queryCountUnCheck();
		session.setAttribute("unCheckcount", count);
		return true;
	}
}
