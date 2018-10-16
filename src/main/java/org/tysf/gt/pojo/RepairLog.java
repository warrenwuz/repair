package org.tysf.gt.pojo;

import java.sql.Timestamp;

/**
 * 维修日志
 * @author wuzhe
 *
 */
public class RepairLog {
     private int id;//自动增长ID
     private String repairLog;//日志记录
     private Timestamp logDate;//日志日期
     private String wpid;//产生日志的维修订单
     
	public RepairLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RepairLog(String repairLog, String wpid) {
		super();
		this.repairLog = repairLog;
		this.wpid = wpid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRepairLog() {
		return repairLog;
	}
	public void setRepairLog(String repairLog) {
		this.repairLog = repairLog;
	}
	public Timestamp getLogDate() {
		return logDate;
	}
	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}
	public String getWpid() {
		return wpid;
	}
	public void setWpid(String wpid) {
		this.wpid = wpid;
	}
     
}
