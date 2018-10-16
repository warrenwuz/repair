package org.tysf.gt.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tysf.gt.pojo.WxOnlineRepairMsg;
import org.tysf.gt.service.IRepairmanService;
import org.tysf.gt.service.IWxOnlineRepairMsgService;
import org.tysf.gt.utils.GsonUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 这个维修信息的查询APi
 * 由于会在微信端对微信信息进行查询、所有统一方法进行维修信息的统一查询
 * @author wuzhe
 *
 */
@Controller
@RequestMapping("api")
public class WxRepairMsgApi {
	@Resource
	private IWxOnlineRepairMsgService wxOnlineRepairMsgService;
	@Resource
	private IRepairmanService repairmanService;
	/***
	 * 维修师傅维修记录查询
	 * 
	 * @param openid
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "repairmanRecode.do", method = RequestMethod.POST)
	public void repairmanRecode(String openid, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, HttpServletResponse response) {
		PageHelper.startPage(pageNumber, pageSize);
		String rmid=repairmanService.queryRmidByopenid(openid);
		List<WxOnlineRepairMsg> wxOnlineRepairMsgs = wxOnlineRepairMsgService.queryrepairmanRecode(rmid);
		PageInfo<WxOnlineRepairMsg> page = new PageInfo<WxOnlineRepairMsg>(wxOnlineRepairMsgs);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("LastPage", page.isIsLastPage());
		data.put("msg", wxOnlineRepairMsgs);
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			String result = GsonUtils.getJsonStr(data);
			writer.write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

}
