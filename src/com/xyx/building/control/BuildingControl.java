package com.xyx.building.control;

import com.xyx.building.service.BuildingService;
import com.xyx.common.BaseControl;
import com.xyx.core.service.PersonService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("BuildingControl")
public class BuildingControl extends BaseControl{
	
	public BuildingService buildingService;
	
	Logger logger=Logger.getLogger(BuildingControl.class);
	
	@RequestMapping("building/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			System.out.println(jsonObject);
			String result=buildingService.save(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("building/delete.do")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			buildingService.delete(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("building/deleteByIds.do")
	public void deleteByIds(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			buildingService.deleteByIds(jsonObject);
			returnSuccess(response);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}
	
	@RequestMapping("building/load.do")
	public void load(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=buildingService.load(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("building", e);
		}
	}
	
	@RequestMapping("building/loadPage.do")
	public void loadPage(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
//			jsonObject.put("pageNo", 1);
//			jsonObject.put("pageSize", 8);
			String result=buildingService.loadPage(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@RequestMapping("building/loadAllArea.do")
	public void loadAllArea(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=JSONArray.fromObject(buildingService.loadAllArea()).toString();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@RequestMapping("building/loadDistrictById.do")
	public void loadDistrictById(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=JSONArray.fromObject(buildingService.loadDistrictById(jsonObject.getInt("key"))).toString();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@RequestMapping("building/loadAllSubway.do")
	public void loadAllSubway(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result=JSONArray.fromObject(buildingService.loadAllSubway()).toString();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@RequestMapping("building/loadAllStatus.do")
	public void loadAllStatus(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result= JSONArray.fromObject(buildingService.loadAllStatus()).toString();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@Autowired
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
}
