package org.tysf.gt.service;

import java.util.List;

import org.tysf.gt.pojo.RepairArea;

public interface IRepairAreaService {

	public List<RepairArea> queryRepairArea();

	public int addRepairAreaGetRaid(String raname);

	public void modifyRepairArea(RepairArea repairArea);

}
