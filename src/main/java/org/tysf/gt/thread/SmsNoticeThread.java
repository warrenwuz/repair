package org.tysf.gt.thread;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.tysf.gt.api.AddressApi;
import org.tysf.gt.pojo.TemplateContent;
import org.tysf.gt.pojo.TemplateData;
import org.tysf.gt.pojo.WxOnlineRepairMsg;
import org.tysf.gt.service.IWxOnlineRepairMsgService;
import org.tysf.gt.utils.SmsUtils;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 短信延迟通知线程
 * 
 * @author wuzhe
 *
 */
@Component
public class SmsNoticeThread extends Thread {
	private String wpid;
	@Resource
	private IService iService;// 发送模板消息的接口
	@Resource
	private IWxOnlineRepairMsgService wxOnlineRepairMsgService;// 报修单的接口
	public SmsNoticeThread() {
		super();
	}

	public SmsNoticeThread(String wpid) {
		super();
		this.wpid = wpid;
	}

	public String getWpid() {
		return wpid;
	}

	public void setWpid(String wpid) {
		this.wpid = wpid;
	}

	@Override
	public void run() {
		WxOnlineRepairMsg wxOnlineRepairMsg = wxOnlineRepairMsgService.queryrepairMsgDetailByWpid(wpid);
		TemplateSender templateSender = new TemplateSender();
		TemplateSender templateSenderStudent = new TemplateSender();
		try {
			// 发送维修师傅的Openid
			String openid = wxOnlineRepairMsg.getRepairman().getOpenid();
			String studentOpenid=wxOnlineRepairMsg.getStudentMsg().getOpenid();
			if(studentOpenid!=null){
			templateSenderStudent.setTouser(studentOpenid);
			templateSenderStudent.setTemplate_id("mt05rk1wBOqlZXlHkOtsyIx2LD9J5nGPZzICWyWM4-s");
			TemplateContent first = new TemplateContent(
					wxOnlineRepairMsg.getStudentMsg().getSname() + "同学你好，你的报修已经派工", "#173177");
			TemplateContent keynote1 = new TemplateContent(wxOnlineRepairMsg.getStudentMsg().getSname(), "#173177");
			TemplateContent keynote2 = new TemplateContent(wxOnlineRepairMsg.getRepairProject().getRpname() + "/"
					+ wxOnlineRepairMsg.getRepairProject().getRepairArea().getRaname(), "#173177");
			TemplateContent keynote3 = new TemplateContent(wxOnlineRepairMsg.getStudentMsg().getFlat().getFname()
					+ "-" + wxOnlineRepairMsg.getStudentMsg().getDormitory() + "宿舍", "#173177");
			TemplateContent remark = new TemplateContent("点击详情维修进度");
			TemplateData data = new TemplateData(first, keynote1, keynote2, keynote3, remark);
			templateSenderStudent.setUrl(AddressApi.Address+"/repairManager/wx/repairProgress.do?wpid="+wxOnlineRepairMsg.getWpid());
			templateSenderStudent.setData(data);
			iService.templateSend(templateSenderStudent);
			}
			if ("".equals(openid) || openid == null) {// 即维修师傅还未绑定微信公众号,立刻发送短信通知
				// 发送短信通知
				SendSmsResponse sendSms = SmsUtils.sendSms(wxOnlineRepairMsg.getRepairman().getRmtel(),wxOnlineRepairMsg.getRepairman().getRmname());
				 System.out.println("Code=" + sendSms.getCode());
			} else {
				templateSender.setTouser(openid);
				templateSender.setTemplate_id("mt05rk1wBOqlZXlHkOtsyIx2LD9J5nGPZzICWyWM4-s");
				TemplateContent first = new TemplateContent(
						wxOnlineRepairMsg.getRepairman().getRmname() + "师傅,您有新的报修订单需要处理", "#173177");
				TemplateContent keynote1 = new TemplateContent(wxOnlineRepairMsg.getStudentMsg().getSname(), "#173177");
				TemplateContent keynote2 = new TemplateContent(wxOnlineRepairMsg.getRepairProject().getRpname() + "/"
						+ wxOnlineRepairMsg.getRepairProject().getRepairArea().getRaname(), "#173177");
				TemplateContent keynote3 = new TemplateContent(wxOnlineRepairMsg.getStudentMsg().getFlat().getFname()
						+ "-" + wxOnlineRepairMsg.getStudentMsg().getDormitory() + "宿舍", "#173177");
				TemplateContent remark = new TemplateContent("点击详情查看");
				TemplateData data = new TemplateData(first, keynote1, keynote2, keynote3, remark);
				templateSender.setUrl(AddressApi.Address+"/repairManager/wx/repairmanAcceptance.do?wpid="+wxOnlineRepairMsg.getWpid());
				templateSender.setData(data);
				iService.templateSend(templateSender);
				Thread.sleep(2000000);//正常模拟是2个小时
				int status = wxOnlineRepairMsgService.queryWxOnlineRepairMsgStatus(wxOnlineRepairMsg.getWpid());
				if (status != 2) {// 说明维修师傅没有接受到通知
			SmsUtils.sendSms(wxOnlineRepairMsg.getRepairman().getRmtel(),wxOnlineRepairMsg.getRepairman().getRmname());
				}
			}
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
