package org.tysf.gt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IRepairAreaDao;
import org.tysf.gt.pojo.RepairArea;
import org.tysf.gt.service.IRepairAreaService;
@Service
public class RepairAreaServiceImpl implements IRepairAreaService {
	@Resource
    private IRepairAreaDao repairAreaDao;
	@Override
	public List<RepairArea> queryRepairArea() {
		return repairAreaDao.queryRepairArea();
	}
	@Override
	public int addRepairAreaGetRaid(String raname) {
		return repairAreaDao.addRepairAreaGetRaid(raname);
	}
	@Override
	public void modifyRepairArea(RepairArea repairArea) {
		repairAreaDao.modifyRepairArea(repairArea);		
	}

}
