package org.tysf.gt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IRepairProjectDao;
import org.tysf.gt.pojo.RepairProject;
import org.tysf.gt.service.IRepairProjectService;
@Service
public class RepairProjectServiceImpl implements IRepairProjectService {
	@Resource
   private IRepairProjectDao repairProjectDao;
	@Override
	public List<RepairProject> queryRepairProject() {
		return repairProjectDao.queryRepairProject();
	}
	@Override
	public List<RepairProject> queryRepairProjectByRaid(int raid) {
		return repairProjectDao.queryRepairProjectByRaid(raid);
	}
	@Override
	public int repairProjectAddGetRpid(RepairProject repairProject) {
		return repairProjectDao.repairProjectAddGetRpid(repairProject);
	}
	@Override
	public void repairProjectModify(RepairProject repairProject) {
          repairProjectDao.repairProjectModify(repairProject);
	}

}
