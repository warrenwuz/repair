package org.tysf.gt.wechat.hanlder;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tysf.gt.api.AddressApi;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
/**
 * 关注的推送内容
 * @author wuzhe
 *
 */
@Repository
public class SubscribeHanlder implements WxMessageHandler {
	@Override
	public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService)
			throws WxErrorException {
		WxXmlOutMessage wxXmlOutMessage=WxXmlOutMessage.TEXT().content("欢迎关注太原师范学院\n在线报修公众平台\n学生绑定<a href='"+AddressApi.Address+"/repairManager/wx/bindWxSystem.do?openid="+wxMessage.getFromUserName()+"'>点击绑定系统</a>\n用户名为学号\n密码为姓名首字母大写+学号 \n例如弓婷大美女:GTDMV"
				+"\n维修人员绑定<a href='"+AddressApi.Address+"/repairManager/wx/bindWxRepairmanSystem.do?openid="+wxMessage.getFromUserName()+"'>点击绑定系统</a>\n用户名为工号\n密码为姓名首字母大写+学号 \n例如弓婷大美女:GTDMV").toUser(wxMessage.getFromUserName())
				.fromUser(wxMessage.getToUserName()).build();
		return wxXmlOutMessage;
	}

}
