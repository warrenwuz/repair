package org.tysf.gt.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;

@Controller
@RequestMapping("/wx.do")
public class WxInitContoller {
	@Resource
	private IService iService;
	@Resource
	private WxMenu menu;
    @Resource
	private WxMessageHandler clickHanlder;
	/*@Autowired
	@Qualifier("sHanlder")*/
    @Resource
	private WxMessageHandler subscribeHanlder;

	@RequestMapping(method = RequestMethod.GET)
	public void WxMain(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 验证服务器的有效性
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
			out.print(echostr);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void WxHanlder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 返回消息给微信服务器
		PrintWriter out = response.getWriter();
		// 创建一个路由器
		WxMessageRouter router = new WxMessageRouter(iService);
		try {
			iService.createMenu(menu, false);
			// 微信服务器推送过来的是XML格式。
			WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
			System.out.println("消息：\n " + wx.toString());
			// 添加规则；这里的规则是指所有消息都交给DemoHandler处理
			// 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
			// end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
			// 把消息传递给路由器进行处理
			router.rule().event(WxConsts.EVT_SUBSCRIBE).handler(subscribeHanlder).end().rule().event(WxConsts.EVT_CLICK).handler(clickHanlder).end();
			WxXmlOutMessage xmlOutMsg = router.route(wx);
			if (xmlOutMsg != null)
				out.print(xmlOutMsg.toXml());// 因为是明文，所以不用加密，直接返回给用户。
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
