package org.tysf.gt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tysf.gt.dao.ISimageDao;
import org.tysf.gt.pojo.SImage;
import org.tysf.gt.service.ISimageService;

@Service
public class SimageServiceImpl implements ISimageService {
	@Resource
	private ISimageDao simageDao;

	@Override
	public void addSImage(SImage sImage) {
		simageDao.addSImage(sImage);
	}

}
