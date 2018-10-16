package org.tysf.gt.dao;

import java.util.List;

import org.tysf.gt.pojo.Flat;

/**
 * 公寓
 * 
 * @author wuzhe
 *
 */
public interface IFlatDao {
	public List<Flat> queryFlatAll();// 查询所有公寓

	public void addFlat(Flat flat);//添加公寓

	public void modifyFlat(Flat flat);//修改公寓信息
}
