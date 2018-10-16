package org.tysf.gt.wechat.hanlder;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.tysf.gt.api.AddressApi;
import org.tysf.gt.service.IRepairmanService;
import org.tysf.gt.service.IStuMsgService;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

@Repository
public class ClickHanlder implements WxMessageHandler {
	@Resource
	private IStuMsgService stuService;
	@Resource
	private IRepairmanService repairmanService;
	// 主机地址
	private static final String address = AddressApi.Address;

	@Override
	public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService)
			throws WxErrorException {
		String openid = wxMessage.getFromUserName();
		String linkStr = "";
		WxXmlOutMessage xmlOutMsg = null;
		String eventKey = wxMessage.getEventKey();
		if (judgeIdentity(eventKey)) {//判断是学生点击还是维修师傅点击
			if (judgeWxStudentAuthorization(openid)) {//判断学生是否绑定系统
				String text = matchesKey(eventKey, openid);
				xmlOutMsg = WxXmlOutMessage.TEXT().content(text).toUser(wxMessage.getFromUserName())
						.fromUser(wxMessage.getToUserName()).build();
				return xmlOutMsg;
			} else {
				linkStr += "<a href='" + address + "/repairManager/wx/bindWxSystem.do?openid=" + openid
						+ "'>点击绑定系统</a>";
				xmlOutMsg = WxXmlOutMessage.TEXT().content("你还未绑定系统" + linkStr).toUser(wxMessage.getFromUserName())
						.fromUser(wxMessage.getToUserName()).build();
				return xmlOutMsg;
			}
		} else {
			if (judgeWxRepairmanAuthorization(openid)) {
				String text = matchesKey(eventKey, openid);
				xmlOutMsg = WxXmlOutMessage.TEXT().content(text).toUser(wxMessage.getFromUserName())
						.fromUser(wxMessage.getToUserName()).build();
				return xmlOutMsg;
			} else {
				linkStr += "<a href='" + address + "/repairManager/wx/bindWxRepairmanSystem.do?openid=" + openid
						+ "'>点击绑定系统</a>";
				xmlOutMsg = WxXmlOutMessage.TEXT().content("你还未绑定系统" + linkStr).toUser(wxMessage.getFromUserName())
						.fromUser(wxMessage.getToUserName()).build();
				return xmlOutMsg;
			}
		}

	}

	/**
	 * 用户身份判断 判断用户学生的身份,是否已经绑定系统 其次判断用户维修员是否绑定系统
	 * 
	 * @param openid
	 * @return
	 */
	public boolean judgeWxStudentAuthorization(String openid) {
		String stuid = stuService.queryStuidByopenid(openid);
		if (stuid != null) {
			return true;
		}
		return false;
	}

	/**
	 * 用户身份判断 判断用户维修人员的身份,是否已经绑定系统 其次判断用户维修员是否绑定系统
	 * 
	 * @param openid
	 * @return
	 */
	public boolean judgeWxRepairmanAuthorization(String openid) {
		String rmid = repairmanService.queryRmidByopenid(openid);
		if (rmid != null) {
			return true;
		}
		return false;
	}

	/**
	 * 匹配点击菜单的用户身份
	 */
	public boolean judgeIdentity(String eventKey) {
		System.out.println(eventKey);
		if (eventKey.toCharArray()[6] == '1') {
			return true;
		}
		return false;
	}

	/**
	 * 匹配事件key
	 */
	public String matchesKey(String eventKey, String openid) {
		String text = "";
		if ("Button11".equals(eventKey)) {
			text = "<a href='" + address + "/repairManager/wx/wxOnlineRepair.do?openid=" + openid + "'>在线报修</a>";
		} else if ("Button12".equals(eventKey)) {
			stuService.unbindByOpenid(openid);//解除绑定
			text = "成功解除绑定";
		} else if ("Button21".equals(eventKey)) {
			text = "<a href='" + address + "/repairManager/wx/repairmanRecode.do?openid=" + openid + "'>点击查看维修记录</a>";
		} else if ("Button22".equals(eventKey)) {
			repairmanService.unbindByOpenid(openid);//解除绑定
			text = "成功解除绑定";
		}
		return text;

	}
}
