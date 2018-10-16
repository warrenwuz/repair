package org.tysf.gt.dao;

import java.util.List;

import org.tysf.gt.pojo.RepairLog;

public interface IRepairLogDao {
	//通过维修ID查找维修日志
    public List<RepairLog> queryRepairLogByWpid(String wpid);
    //添加维修日志
    public void addRepairLog(RepairLog repairLog);
    
}
