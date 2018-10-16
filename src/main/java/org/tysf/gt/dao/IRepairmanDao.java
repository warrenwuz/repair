package org.tysf.gt.dao;

import java.util.List;
import java.util.Map;

import org.tysf.gt.pojo.Repairman;

public interface IRepairmanDao {
	public void addRepairman(Repairman repairman);

	public List<Repairman> queryRepariman();

	public void addRepairProjectByRmid(Map<String, Object> params);

	public void modifyRepairmanNameAndPassword(Repairman repairman);

	public void modifyRepairmanTel(Repairman repairman);
    //查找用户是否绑定了该系统
	public String queryRmidByopenid(String openid);
	//工号和密码登录系统
	public Repairman bindWxRepairmanSystem(Repairman repairman);
	//添加/更新openid
	public void updateOpenidByRmid(Repairman repairman);
    //解除绑定
	public void unbindByOpenid(String openid);
}
