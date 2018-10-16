package org.tysf.gt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.tysf.gt.pojo.WxOnlineRepairMsg;

public interface IWxOnlineRepairMsgDao {
     //添加保修信息
	public void addWxOnlineRepairMsg(WxOnlineRepairMsg wxOnlineRepairMsg);
	//如果上传了图片,则为报修单添加图片
	public void addSImagesByWpid(Map<String, Object> params);
	//查找保修信息
	public List<WxOnlineRepairMsg> queryWxOnlineRepairMsg(@Param("status")int status);
	//查询没有审核的数量
	public int queryCountUnCheck();
	//查询报修细节
	public WxOnlineRepairMsg queryrepairMsgDetailByWpid(String wpid);
	//派工
	public void addWxOnlineRepairmanAndRemark(WxOnlineRepairMsg wxOnlineRepairMsg);
	//查询报修单的状态
	public int queryWxOnlineRepairMsgStatus(String wpid);
	//查找空闲师傅
	public String queryRepairmanForFree();
	//查找维修师傅受理信息
	public WxOnlineRepairMsg queryrepairmanAcceptanceByWpid(String wpid);
	//更新报修单状态
	public void updateStatusByWpid(String wpid);
	//查找维修记录
	public List<WxOnlineRepairMsg> queryrepairmanRecode(String rmid);
	//提交完工(完工备注和更新保修单状态)
	public void addCompleteRemarkAndStatusByWpid(WxOnlineRepairMsg wxOnlineRepairMsg);
	//验收订单
	public void checkAndAcceptance(String wpid);

	

}
