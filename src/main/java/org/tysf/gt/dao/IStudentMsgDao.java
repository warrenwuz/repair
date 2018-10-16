package org.tysf.gt.dao;

import java.util.List;

import org.tysf.gt.pojo.StudentMsg;

public interface IStudentMsgDao {
	public List<StudentMsg> queryStudentMsg();// 查询学生信息

	public void modifyStuMsg(StudentMsg studentMsg);// 修改学生信息

	public void addstuMsgManager(StudentMsg studentMsg);// 添加学生信息

	public String queryStuidByopenid(String openid);// 查询学生是否绑定了公众号

	public StudentMsg queryStudentMsgByStuidAndPassword(StudentMsg studentMsg);// 密码和学号进行匹配验证用户

	public void updateOpenidByStuid(StudentMsg studentMsg);// 添加openid值

	public StudentMsg queryStudentMsgByOpenid(String openid);// 通过微信公众号唯一标识查找学生信息

	public void unbindByOpenid(String openid);//解除绑定
}
