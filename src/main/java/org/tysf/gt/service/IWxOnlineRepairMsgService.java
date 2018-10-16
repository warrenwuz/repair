package org.tysf.gt.service;

import java.util.List;
import java.util.Map;

import org.tysf.gt.pojo.WxOnlineRepairMsg;

public interface IWxOnlineRepairMsgService {
   public void addWxOnlineRepairMsg(WxOnlineRepairMsg wxOnlineRepairMsg);
   public void addSImagesByWpid(Map<String,Object>params);
   public List<WxOnlineRepairMsg> queryWxOnlineRepairMsg(int status);
   public int queryCountUnCheck();//查询没有审核的数量
   public WxOnlineRepairMsg queryrepairMsgDetailByWpid(String wpid);//查询报修细节
   public void dispatchWxOnlineRepairMsg(WxOnlineRepairMsg wxOnlineRepairMsg);//派工详情
   public int queryWxOnlineRepairMsgStatus(String wpid);//查询保修单的状态
   public String queryRepairmanForFree();//查找空闲的维修工人
   public WxOnlineRepairMsg queryrepairmanAcceptanceByWpid(String wpid);//查找维修工人受理的信息
   public void updateStatusByWpid(String wpid);//更新报修单的状态
   public List<WxOnlineRepairMsg> queryrepairmanRecode(String rmid);//查找维修记录
   public void addCompleteRemarkAndStatusByWpid(WxOnlineRepairMsg wxOnlineRepairMsg);//提交完工(完工备注和更新保修单状态)
    public void checkAndAcceptance(String wpid);//验收报修单

}
