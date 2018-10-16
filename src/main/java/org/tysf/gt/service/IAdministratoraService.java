package org.tysf.gt.service;

import java.util.List;

import org.tysf.gt.pojo.Administrator;

public interface IAdministratoraService {

 public List<Administrator> queryAdmin();

public Administrator queryAdminByIdAndPassword(Administrator administrator);

public void addAdministrator(Administrator administrator);

public void modifyAdministratorNameAndPassword(Administrator administrator);

public void modifyAdministratorTel(Administrator administrator);

}
