package org.tysf.gt.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IStudentMsgDao;
import org.tysf.gt.pojo.StudentMsg;
import org.tysf.gt.service.IStuMsgService;
import org.tysf.gt.utils.MD5Utils;
@Service
public class StuMsgServiceImpl  implements IStuMsgService{
	@Resource
   private IStudentMsgDao studentMsgDao;
	@Override
	public List<StudentMsg> queryStudentMsg() {
		return studentMsgDao.queryStudentMsg();
	}
	@Override
	public void modifyStuMsg(StudentMsg studentMsg) {
		studentMsgDao.modifyStuMsg(studentMsg);	
	}
	@Override
	public void addstuMsgManager(StudentMsg studentMsg) {
		studentMsgDao.addstuMsgManager(studentMsg);
		
	}
	@Override
	public String queryStuidByopenid(String openid) {
		return studentMsgDao.queryStuidByopenid(openid);
	}
	//判断用户是否已经绑定过微信公众号
	@Override
	public StudentMsg bindWxSystem(StudentMsg studentMsg) {
		studentMsg.setPassword(MD5Utils.md5Password(studentMsg.getPassword()));
		return studentMsgDao.queryStudentMsgByStuidAndPassword(studentMsg);
	}
	@Override
	public void updateOpenidByStuid(StudentMsg studentMsg) {
          studentMsgDao.updateOpenidByStuid(studentMsg);		
	}
	@Override
	public StudentMsg queryStudentMsgByOpenid(String openid) {
		return studentMsgDao.queryStudentMsgByOpenid(openid);
	}
	@Override
	public void unbindByOpenid(String openid) {//解除绑定
		studentMsgDao.unbindByOpenid(openid);//解除绑定
		
	}

}
