package org.tysf.gt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IAdministratorDao;
import org.tysf.gt.pojo.Administrator;
import org.tysf.gt.service.IAdministratoraService;

@Service
public class AdministratorServiceImpl implements IAdministratoraService {
	@Resource
	private IAdministratorDao administratorDao;

	@Override
	public List<Administrator> queryAdmin() {
		return administratorDao.queryAdmin();
	}

	@Override
	public Administrator queryAdminByIdAndPassword(Administrator administrator) {
		return administratorDao.queryAdminByIdAndPassword(administrator);
	}

	@Override
	public void addAdministrator(Administrator administrator) {
		administratorDao.addAdministrator(administrator);
	}

	@Override
	public void modifyAdministratorNameAndPassword(Administrator administrator) {
		administratorDao.modifyAdministratorNameAndPassword(administrator);	
	}

	@Override
	public void modifyAdministratorTel(Administrator administrator) {
		administratorDao.modifyAdministratorTel(administrator);
	}

}
