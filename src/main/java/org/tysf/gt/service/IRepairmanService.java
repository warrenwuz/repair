package org.tysf.gt.service;

import java.util.List;
import java.util.Map;

import org.tysf.gt.pojo.RepairProject;
import org.tysf.gt.pojo.Repairman;

public interface IRepairmanService {
	   public void addRepairman(Repairman repairman);
	   public List<Repairman> queryRepariman();
	   //通过维修工人的工号设置维修范围
	public void addRepairProjectByRmid(Map<String, Object> params);
	public List<RepairProject> queryProjectByRmid(String rmid);
	public List<RepairProject> queryUnSelectRepairProject(String rmid);
	public void modifyRepairmanNameAndPassword(Repairman repairman);
	public void modifyRepairmanTel(Repairman repairman);
	public String queryRmidByopenid(String openid);//查找用户是否绑定了系统
	public Repairman bindWxRepairmanSystem(Repairman repairman);//工号和密码登录系统
	public void updateOpenidByRmid(Repairman repairman);//添加openid
	public void unbindByOpenid(String openid);//解除绑定

}
