package org.tysf.gt.pojo;

/**
 * 报修项目实体类
 * 
 * @author wuzhe
 *
 */
public class RepairProject {
	private int rpid;
	private String rpname;
	private RepairArea repairArea;// 报修区域

	public RepairProject() {
		super();
		// TODO Auto-generated constructor stub
	}
     
	public RepairProject(int rpid) {
		super();
		this.rpid = rpid;
	}

	public RepairProject(String rpname, RepairArea repairArea) {
		super();
		this.rpname = rpname;
		this.repairArea = repairArea;
	}

	public RepairProject(int rpid, String rpname, RepairArea repairArea) {
		super();
		this.rpid = rpid;
		this.rpname = rpname;
		this.repairArea = repairArea;
	}

	public int getRpid() {
		return rpid;
	}

	public void setRpid(int rpid) {
		this.rpid = rpid;
	}

	public String getRpname() {
		return rpname;
	}

	public void setRpname(String rpname) {
		this.rpname = rpname;
	}

	public RepairArea getRepairArea() {
		return repairArea;
	}

	public void setRepairArea(RepairArea repairArea) {
		this.repairArea = repairArea;
	}

	@Override
	public String toString() {
		return "RepairProject [rpid=" + rpid + ", rpname=" + rpname + ", repairArea=" + repairArea + "]";
	}

}
