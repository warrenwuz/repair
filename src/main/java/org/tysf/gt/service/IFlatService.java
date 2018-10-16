package org.tysf.gt.service;

import java.util.List;

import org.tysf.gt.pojo.Flat;

public interface IFlatService {
	public List<Flat> queryFlatAll();//查询所有公寓
	public void addFlat(Flat flat);//添加公寓
	public void modifyFlat(Flat flat);//修改公寓信息
}
