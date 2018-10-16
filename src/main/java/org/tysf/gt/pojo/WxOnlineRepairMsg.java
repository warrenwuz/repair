package org.tysf.gt.pojo;

import java.sql.Timestamp;
import java.util.List;

/**
 * 微信在线报修单实体类
 * 
 * @author wuzhe
 *
 */
public class WxOnlineRepairMsg {
	private String wpid;// 微信报修单流水号(使用UUID)
	private StudentMsg studentMsg;// 学生信息
	private String dormitory;// 宿舍号
	private RepairProject repairProject;// 报修项目
	private String projectdetail;// 报修情况描述
	private int status;// 维修状态
	private Timestamp timestamp;// 报修时间
	private String tel;// 保修联系方式
	private List<SImage> simage;// 保修图片
	private Administrator arrangeAdmin;// 报修单的派工管理员
	private Administrator checkAdmin;// 报修单的验收管理员
	private Repairman repairman;// 报修单的维修管理员
	private String remark;// 备注
	private int dispatchingType;// 派工类型 0位自动派工 1位手动派工
	private String completeremark;// 完工备注
	private List<RepairLog> repairLog;//维修日志
	public WxOnlineRepairMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxOnlineRepairMsg(String wpid, RepairProject repairProject, String projectdetail, Timestamp timestamp,
			List<SImage> simage) {
		super();
		this.wpid = wpid;
		this.repairProject = repairProject;
		this.projectdetail = projectdetail;
		this.timestamp = timestamp;
		this.simage = simage;
	}

	public WxOnlineRepairMsg(String wpid, Flat flat, String dormitory, RepairProject repairProject,
			String projectdetail, Timestamp timestamp) {
		super();
		this.wpid = wpid;
		this.dormitory = dormitory;
		this.repairProject = repairProject;
		this.projectdetail = projectdetail;
		this.timestamp = timestamp;
	}

	public WxOnlineRepairMsg(String wpid, StudentMsg studentMsg, String dormitory, RepairProject repairProject,
			String projectdetail, int status, Timestamp timestamp, String tel, List<SImage> simage,
			Administrator arrangeAdmin, Administrator checkAdmin, Repairman repairman, String remark,
			int dispatchingType) {
		super();
		this.wpid = wpid;
		this.studentMsg = studentMsg;
		this.dormitory = dormitory;
		this.repairProject = repairProject;
		this.projectdetail = projectdetail;
		this.status = status;
		this.timestamp = timestamp;
		this.tel = tel;
		this.simage = simage;
		this.arrangeAdmin = arrangeAdmin;
		this.checkAdmin = checkAdmin;
		this.repairman = repairman;
		this.remark = remark;
		this.dispatchingType = dispatchingType;
	}

	public WxOnlineRepairMsg(String wpid, StudentMsg studentMsg, String dormitory, RepairProject repairProject,
			String projectdetail, int status, Timestamp timestamp, String tel, List<SImage> simage,
			Administrator arrangeAdmin, Administrator checkAdmin, Repairman repairman, String remark,
			int dispatchingType, String completeremark) {
		super();
		this.wpid = wpid;
		this.studentMsg = studentMsg;
		this.dormitory = dormitory;
		this.repairProject = repairProject;
		this.projectdetail = projectdetail;
		this.status = status;
		this.timestamp = timestamp;
		this.tel = tel;
		this.simage = simage;
		this.arrangeAdmin = arrangeAdmin;
		this.checkAdmin = checkAdmin;
		this.repairman = repairman;
		this.remark = remark;
		this.dispatchingType = dispatchingType;
		this.completeremark = completeremark;
	}

	public String getWpid() {
		return wpid;
	}

	public void setWpid(String wpid) {
		this.wpid = wpid;
	}

	public String getDormitory() {
		return dormitory;
	}

	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}

	public RepairProject getRepairProject() {
		return repairProject;
	}

	public void setRepairProject(RepairProject repairProject) {
		this.repairProject = repairProject;
	}

	public String getProjectdetail() {
		return projectdetail;
	}

	public void setProjectdetail(String projectdetail) {
		this.projectdetail = projectdetail;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public StudentMsg getStudentMsg() {
		return studentMsg;
	}

	public void setStudentMsg(StudentMsg studentMsg) {
		this.studentMsg = studentMsg;
	}

	public Administrator getArrangeAdmin() {
		return arrangeAdmin;
	}

	public void setArrangeAdmin(Administrator arrangeAdmin) {
		this.arrangeAdmin = arrangeAdmin;
	}

	public Administrator getCheckAdmin() {
		return checkAdmin;
	}

	public void setCheckAdmin(Administrator checkAdmin) {
		this.checkAdmin = checkAdmin;
	}

	public Repairman getRepairman() {
		return repairman;
	}

	public void setRepairman(Repairman repairman) {
		this.repairman = repairman;
	}

	public List<SImage> getSimage() {
		return simage;
	}

	public void setSimage(List<SImage> simage) {
		this.simage = simage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDispatchingType() {
		return dispatchingType;
	}

	public void setDispatchingType(int dispatchingType) {
		this.dispatchingType = dispatchingType;
	}

	public String getCompleteremark() {
		return completeremark;
	}

	public void setCompleteremark(String completeremark) {
		this.completeremark = completeremark;
	}
    
	public List<RepairLog> getRepairLog() {
		return repairLog;
	}

	public void setRepairLog(List<RepairLog> repairLog) {
		this.repairLog = repairLog;
	}

	@Override
	public String toString() {
		return "WxOnlineRepairMsg [wpid=" + wpid + ", studentMsg=" + studentMsg + ", dormitory=" + dormitory
				+ ", repairProject=" + repairProject + ", projectdetail=" + projectdetail + ", status=" + status
				+ ", timestamp=" + timestamp + ", tel=" + tel + ", simage=" + simage + ", arrangeAdmin=" + arrangeAdmin
				+ ", checkAdmin=" + checkAdmin + ", repairman=" + repairman + ", remark=" + remark
				+ ", dispatchingType=" + dispatchingType + ", completeremark=" + completeremark + "]";
	}

}
