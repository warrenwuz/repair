package org.tysf.gt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IRepairLogDao;
import org.tysf.gt.dao.IStudentMsgDao;
import org.tysf.gt.pojo.RepairLog;
import org.tysf.gt.service.IRepairLogService;
@Service
public class RepairLogServiceImpl implements IRepairLogService {
	@Resource
	private IRepairLogDao repairLogDao;
	@Resource
	private IStudentMsgDao studentMSgDao;

	@Override
	public List<RepairLog> queryRepairLogByWpid(String wpid) {
		return repairLogDao.queryRepairLogByWpid(wpid);
	}

	@Override
	public void addRepairLog(RepairLog repairLog) {
		repairLogDao.addRepairLog(repairLog);
	}
 
}
