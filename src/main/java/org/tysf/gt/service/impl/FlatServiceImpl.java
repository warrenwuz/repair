package org.tysf.gt.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.IFlatDao;
import org.tysf.gt.pojo.Flat;
import org.tysf.gt.service.IFlatService;
@Service
public class FlatServiceImpl implements IFlatService{
   @Resource
    IFlatDao flatDao;
	@Override
	public List<Flat> queryFlatAll() {
		return flatDao.queryFlatAll();
	}
	@Override
	public void addFlat(Flat flat) {
       flatDao.addFlat(flat);		
	}
	@Override
	public void modifyFlat(Flat flat) {
		  flatDao.modifyFlat(flat);	
		
	}

}
