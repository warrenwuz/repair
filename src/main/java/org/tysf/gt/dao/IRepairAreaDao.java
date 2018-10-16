package org.tysf.gt.dao;

import java.util.List;

import org.tysf.gt.pojo.RepairArea;

public interface IRepairAreaDao {

	public List<RepairArea> queryRepairArea();

	public int addRepairAreaGetRaid(String raname);

	public void modifyRepairArea(RepairArea repairArea);

}
