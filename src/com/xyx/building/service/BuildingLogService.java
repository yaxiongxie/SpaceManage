package com.xyx.building.service;

import com.xyx.building.bean.BuildingLog;
import com.xyx.building.bean.PublishOfficebuildinglist;
import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.bean.*;
import com.xyx.common.json.PropertyStrategyWrapper;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("BuildingLogService")
public class BuildingLogService extends BaseService {

	public String save(JSONObject jsonObject) throws Exception {
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonConfig config = new JsonConfig();
		config.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
		config.setRootClass(BuildingLog.class);
		BuildingLog buildingLog=(BuildingLog) JSONObject.toBean(jsonObject,config);
		buildingLog.setCreatetime(dateFormat.format(new Date()));
		saveOrUpdate(buildingLog);
		JSONObject returnObject=JSONObject.fromObject(buildingLog);
		return returnObject.toString();
	}


	public void delete(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(BuildingLog.class, id);
	}

	public void deleteByIds(JSONObject jsonObject) throws Exception {
		String ids=jsonObject.getString("ids");
		for(String id:ids.split(",")){
			if(id.equals("")){
				continue;
			}
			deleteById(BuildingLog.class, Integer.parseInt(id));
		}
	}

	public String loadPage(JSONObject jsonObject) throws Exception{
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		String hqlString="from BuildingLog  where relateId=?";

		Page page=findPageByFetchedHql(hqlString, null, pageNo, pageSize, new Object[]{jsonObject.getString("relateId")});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return JSONObject.fromObject(page).toString();
	}

}
