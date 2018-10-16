package org.tysf.gt.dao;

import java.util.List;

import org.tysf.gt.pojo.RepairProject;

public interface IRepairProjectDao {

	public List<RepairProject> queryRepairProject();

	public List<RepairProject> queryRepairProjectByRaid(int raid);

	public int repairProjectAddGetRpid(RepairProject repairProject);

	public void repairProjectModify(RepairProject repairProject);
	//通过维修工人工号查找维修范围
	public List<RepairProject> queryProjectByRmid(String rmid);
    //查找维修工人还有选择的维修范围
	public List<RepairProject> queryUnSelectRepairProject(String rmid);

}
