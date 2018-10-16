package org.tysf.gt.dao;
import java.util.List;

import org.tysf.gt.pojo.Administrator;
public interface IAdministratorDao {
    //查找全部的管理人员
	public  List<Administrator> queryAdmin();
	//通过管理员ID和密码查找管理员
	public Administrator queryAdminByIdAndPassword(Administrator administrator);
	//添加管理人员信息
	public void addAdministrator(Administrator administrator);
	//更新管理员名字并更新密码(因为密码是由管理员的工号和名字得来的,更新了管理名字密码必须更新)
	public void modifyAdministratorNameAndPassword(Administrator administrator);
	//更新管理员电话
	public void modifyAdministratorTel(Administrator administrator);

}
