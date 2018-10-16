package org.tysf.gt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IRepairProjectDao;
import org.tysf.gt.dao.IRepairmanDao;
import org.tysf.gt.pojo.RepairProject;
import org.tysf.gt.pojo.Repairman;
import org.tysf.gt.service.IRepairmanService;
@Service
public class RepairmanServiceImpl implements IRepairmanService{
@Resource
private IRepairmanDao repairmanDao;
@Resource
private IRepairProjectDao repairProjectDao;
	@Override
	public void addRepairman(Repairman repairman) {
             repairmanDao.addRepairman(repairman);		
	}
	@Override
	public List<Repairman> queryRepariman() {
		return repairmanDao.queryRepariman();
	}
	@Override
	public void addRepairProjectByRmid(Map<String, Object> params) {
		repairmanDao.addRepairProjectByRmid(params);
		
	}
	@Override
	public List<RepairProject> queryProjectByRmid(String rmid) {
		return repairProjectDao.queryProjectByRmid(rmid);
	}
	@Override
	public List<RepairProject> queryUnSelectRepairProject(String rmid) {
		return repairProjectDao.queryUnSelectRepairProject(rmid);
	}
	@Override
	public void modifyRepairmanNameAndPassword(Repairman repairman) {
		repairmanDao.modifyRepairmanNameAndPassword(repairman);
	}
	@Override
	public void modifyRepairmanTel(Repairman repairman) {
		repairmanDao.modifyRepairmanTel(repairman);
	}
	@Override
	public String queryRmidByopenid(String openid) {
		return repairmanDao.queryRmidByopenid(openid);
	}
	@Override
	public Repairman bindWxRepairmanSystem(Repairman repairman) {
		return repairmanDao.bindWxRepairmanSystem(repairman);
	}
	@Override
	public void updateOpenidByRmid(Repairman repairman) {
		repairmanDao.updateOpenidByRmid(repairman);
		
	}
	@Override
	public void unbindByOpenid(String openid) {
		repairmanDao.unbindByOpenid(openid);
		
	}

}
