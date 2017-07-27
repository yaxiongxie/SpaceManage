package com.xyx.building.control;

import com.xyx.building.service.BuildingLogService;
import com.xyx.building.service.OfficeService;
import com.xyx.common.BaseControl;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("BuildingLogControl")
public class BuildingLogControl extends BaseControl{
	
	public BuildingLogService buildingLogService;
	
	Logger logger=Logger.getLogger(BuildingLogControl.class);
	
	@RequestMapping("buildinglog/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			jsonObject.put("operater",getLoginPerson(request).getRealname());
			String result=buildingLogService.save(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("buildinglog/delete.do")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			buildingLogService.delete(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("buildinglog/deleteByIds.do")
	public void deleteByIds(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			buildingLogService.deleteByIds(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}


	@RequestMapping("buildinglog/loadPage.do")
	public void loadPage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=buildingLogService.loadPage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@Autowired
	public void setBuildingLogService(BuildingLogService buildingLogService) {
		this.buildingLogService = buildingLogService;
	}
}
