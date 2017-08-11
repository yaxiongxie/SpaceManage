package com.xyx.building.control;

import com.xyx.building.bean.PublishOfficebuildinglist;
import com.xyx.building.bean.PublishOfficelist;
import com.xyx.building.service.BuildingService;
import com.xyx.common.BaseControl;
import com.xyx.common.StringUtil;
import com.xyx.common.processfile.FileTool;
import com.xyx.common.redis.RedisUtil;
import com.xyx.core.bean.CoreAttachment;
import com.xyx.core.service.PersonService;
import com.xyx.document.bean.Document;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("BuildingControl")
public class BuildingControl extends BaseControl{

	public String filePath= RedisUtil.getValue("filePath");
	
	public BuildingService buildingService;
	
	Logger logger=Logger.getLogger(BuildingControl.class);

	DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("building/save.do")
	public void save(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			System.out.println(jsonObject);
			String result=buildingService.save(jsonObject);
			returnJson(response, result);
		}catch (Exception e) {
			e.printStackTrace();
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
			System.out.println("building json===>>>"+result);
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

	@RequestMapping("building/loadAllDecorate.do")
	public void loadAllDecorate(HttpServletRequest request,HttpServletResponse response){
		try{
			JSONObject jsonObject=getJSONData(request);
			String result= JSONArray.fromObject(buildingService.loadAllDecorate()).toString();
			returnJson(response, result);
		}catch (Exception e) {
			logger.error("building", e);
		}
	}

	@RequestMapping("building/refreshUpload.do")
	public void refreshUpload(HttpServletRequest request,HttpServletResponse response){

		String relateid=request.getParameter("relateid");
		String tableName=request.getParameter("tablename");
		List<String> list=buildingService.getListByHQL("select filename from CoreAttachment where relationid=? and tablename=? order by id",Integer.parseInt(relateid),tableName);
		if(tableName.equals("building")){
			PublishOfficebuildinglist officebuildinglist=buildingService.get(PublishOfficebuildinglist.class,Integer.parseInt(relateid));
			if(list.size()>0){
				officebuildinglist.setImage1(list.get(0));
			}
			if(list.size()>1){
				officebuildinglist.setImage2(list.get(1));
			}
			if(list.size()>2){
				officebuildinglist.setImage3(list.get(2));
			}
			if(list.size()>3){
				officebuildinglist.setImage4(list.get(3));
			}
			if(list.size()>4){
				officebuildinglist.setImage5(list.get(4));
			}
			if(list.size()>5){
				officebuildinglist.setImage6(list.get(5));
			}
			buildingService.saveOrUpdate(officebuildinglist);
		}
		if(tableName.equals("office")){
			PublishOfficelist officelist=buildingService.get(PublishOfficelist.class,Integer.parseInt(relateid));
			if(list.size()>0){
				officelist.setImage1(list.get(0));
			}
			if(list.size()>1){
				officelist.setImage2(list.get(1));
			}
			if(list.size()>2){
				officelist.setImage3(list.get(2));
			}
			if(list.size()>3){
				officelist.setImage4(list.get(3));
			}
			if(list.size()>4){
				officelist.setImage5(list.get(4));
			}
			if(list.size()>5){
				officelist.setImage6(list.get(5));
			}
			buildingService.saveOrUpdate(officelist);
		}

		returnSuccess(response);
	}

	@RequestMapping("building/uploadFile.do")
	public void uploadFiles(HttpServletRequest request,HttpServletResponse response){
		try{
			FileUtils.forceMkdir(new File(filePath));
		}catch (Exception e){
			e.printStackTrace();
		}
		String relateid=request.getParameter("relateid");
		String tableName=request.getParameter("tablename");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file != null){
					byte[] filebytes=null;
					try {
						filebytes = file.getBytes();
					} catch (IOException e) {
						e.printStackTrace();
					}

					UUID uuid=UUID.randomUUID();
					String originalName=file.getOriginalFilename();
					String filename=originalName.substring(0,originalName.indexOf("."))+"_"+uuid.toString()+originalName.substring(originalName.indexOf("."));

					CoreAttachment attachment=new CoreAttachment();
					attachment.setCreatetime(dateFormat.format(new Date()));

					attachment.setFiledata(filebytes);
					attachment.setFilename("pic_folder/"+filename);
					attachment.setFilesize(filebytes.length);
					attachment.setFiletype(file.getContentType());
					attachment.setRelationid(Integer.parseInt(relateid));
					attachment.setTablename(tableName);
					buildingService.saveOrUpdate(attachment);

					try {
						FileUtils.writeByteArrayToFile(new File(filePath + filename), filebytes);
					}catch (Exception e){
						e.printStackTrace();
					}
//                    SolrjTool.AddDoc(document);
				}
			}
		}

		returnSuccess(response);
	}

	@RequestMapping("building/deleteImages.do")
	public void deleteImages(HttpServletRequest request,HttpServletResponse response){

		String relateid=request.getParameter("relateid");
		String tableName=request.getParameter("tablename");
		buildingService.queryHql("delete CoreAttachment where relationid=? and tablename=?",Integer.parseInt(relateid),tableName);
		PublishOfficebuildinglist officebuildinglist=buildingService.get(PublishOfficebuildinglist.class,Integer.parseInt(relateid));
		officebuildinglist.setImage1("");
		officebuildinglist.setImage2("");
		officebuildinglist.setImage3("");
		officebuildinglist.setImage4("");
		officebuildinglist.setImage5("");
		officebuildinglist.setImage6("");
		buildingService.saveOrUpdate(officebuildinglist);
		returnSuccess(response);
	}

	@Autowired
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
}
