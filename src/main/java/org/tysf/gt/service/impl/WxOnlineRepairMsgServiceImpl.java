package org.tysf.gt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IWxOnlineRepairMsgDao;
import org.tysf.gt.pojo.WxOnlineRepairMsg;
import org.tysf.gt.service.IWxOnlineRepairMsgService;
@Service
public class WxOnlineRepairMsgServiceImpl implements IWxOnlineRepairMsgService {
	@Resource
	private IWxOnlineRepairMsgDao wxOnlineRepairMsgDao;
	@Override
	public void addWxOnlineRepairMsg(WxOnlineRepairMsg wxOnlineRepairMsg) {
		wxOnlineRepairMsgDao.addWxOnlineRepairMsg(wxOnlineRepairMsg);	
	}
	@Override
	public void addSImagesByWpid(Map<String, Object> params) {
		wxOnlineRepairMsgDao.addSImagesByWpid(params);		
	}
	@Override
	public int queryCountUnCheck() {
		return wxOnlineRepairMsgDao.queryCountUnCheck();
	}
	@Override
	public List<WxOnlineRepairMsg> queryWxOnlineRepairMsg(int status) {
		return wxOnlineRepairMsgDao.queryWxOnlineRepairMsg(status);
	}
	@Override
	public WxOnlineRepairMsg queryrepairMsgDetailByWpid(String wpid) {
		return wxOnlineRepairMsgDao.queryrepairMsgDetailByWpid(wpid);
	}
	@Override
	public void dispatchWxOnlineRepairMsg(WxOnlineRepairMsg wxOnlineRepairMsg) {
		wxOnlineRepairMsgDao.addWxOnlineRepairmanAndRemark(wxOnlineRepairMsg);
	}
	@Override
	public int queryWxOnlineRepairMsgStatus(String wpid) {
		return wxOnlineRepairMsgDao.queryWxOnlineRepairMsgStatus(wpid);
	}
	@Override
	public String queryRepairmanForFree() {
		return wxOnlineRepairMsgDao.queryRepairmanForFree();
	}
	@Override
	public WxOnlineRepairMsg queryrepairmanAcceptanceByWpid(String wpid) {
		return wxOnlineRepairMsgDao.queryrepairmanAcceptanceByWpid(wpid);
	}
	@Override
	public void updateStatusByWpid(String wpid) {
		wxOnlineRepairMsgDao.updateStatusByWpid(wpid);
	}
	@Override
	public List<WxOnlineRepairMsg> queryrepairmanRecode(String rmid) {
		return wxOnlineRepairMsgDao.queryrepairmanRecode(rmid);
	}
	@Override
	public void addCompleteRemarkAndStatusByWpid(WxOnlineRepairMsg wxOnlineRepairMsg) {
		wxOnlineRepairMsgDao.addCompleteRemarkAndStatusByWpid(wxOnlineRepairMsg);
	}
	@Override
	public void checkAndAcceptance(String wpid) {
		wxOnlineRepairMsgDao.checkAndAcceptance(wpid);
		
	}
	

}
