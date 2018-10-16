package org.tysf.gt.pojo;

import java.util.List;

/**
 * 维修人员实体类
 * @author wuzhe
 *
 */
public class Repairman {
    private String rmid;//工号
    private String rmname;//姓名
    private String rmtel;//电话
    private String rmpassword;//密码
    private List<RepairProject> repairProject;//负责维修项目
    private String rmopenid;//绑定微信的唯一标识
	public Repairman() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Repairman(String rmid) {
		super();
		this.rmid = rmid;
	}

	public Repairman(String rmid, String rmname, String rmtel, String rmpassword, List<RepairProject> repairProject,
			String openid) {
		super();
		this.rmid = rmid;
		this.rmname = rmname;
		this.rmtel = rmtel;
		this.rmpassword = rmpassword;
		this.repairProject = repairProject;
		this.rmopenid = openid;
	}

	public String getRmid() {
		return rmid;
	}
	public void setRmid(String rmid) {
		this.rmid = rmid;
	}
	public String getRmname() {
		return rmname;
	}
	public void setRmname(String rmname) {
		this.rmname = rmname;
	}
	public String getRmtel() {
		return rmtel;
	}
	public void setRmtel(String rmtel) {
		this.rmtel = rmtel;
	}
	public String getRmpassword() {
		return rmpassword;
	}
	public void setRmpassword(String rmpassword) {
		this.rmpassword =rmpassword;
	}
	public List<RepairProject> getRepairProject() {
		return repairProject;
	}
	public void setRepairProject(List<RepairProject> repairProject) {
		this.repairProject = repairProject;
	}
	
	public String getOpenid() {
		return rmopenid;
	}

	public void setOpenid(String openid) {
		this.rmopenid = openid;
	}

	@Override
	public String toString() {
		return "Repairman [rmid=" + rmid + ", rmname=" + rmname + ", rmtel=" + rmtel + ", rmpassword=" + rmpassword
				+ ", repairProject=" + repairProject + ", rmopenid=" + rmopenid + "]";
	}


    
}
