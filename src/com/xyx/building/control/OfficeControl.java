package com.xyx.building.control;

import com.xyx.building.service.OfficeService;
import com.xyx.common.BaseControl;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("OfficeControl")
public class OfficeControl extends BaseControl{
	
	public OfficeService officeService;
	
	Logger logger=Logger.getLogger(OfficeControl.class);
	
	@RequestMapping("office/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=officeService.save(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("office/delete.do")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			officeService.delete(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("office/deleteByIds.do")
	public void deleteByIds(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			officeService.deleteByIds(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("office/load.do")
	public void load(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=officeService.load(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("building", e);
		}
	}
	
	@RequestMapping("office/loadPage.do")
	public void loadPage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
//			jsonObject.put("pageNo", 1);
//			jsonObject.put("pageSize", 8);
			String result=officeService.loadPage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@Autowired
	public void setOfficeService(OfficeService officeService) {
		this.officeService = officeService;
	}
}
