package org.tysf.gt.service;

import java.util.List;

import org.tysf.gt.pojo.RepairProject;

public interface IRepairProjectService {

	public List<RepairProject> queryRepairProject();

	public List<RepairProject> queryRepairProjectByRaid(int raid);

	public int repairProjectAddGetRpid(RepairProject repairProject);

	public void repairProjectModify(RepairProject repairProject);

}
