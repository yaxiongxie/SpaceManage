package com.xyx.building.service;

import com.xyx.building.bean.PublishOfficebuildinglist;
import com.xyx.building.bean.PublishOfficelist;
import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.bean.CommonDataDecoratedegree;
import com.xyx.common.bean.CommonDataDistrict;
import com.xyx.common.bean.CommonDataStatus;
import com.xyx.common.json.PropertyStrategyWrapper;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertySetStrategy;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("OfficeService")
public class OfficeService extends BaseService {

	public String save(JSONObject jsonObject) throws Exception {
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonConfig config = new JsonConfig();
		config.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
		config.setRootClass(PublishOfficelist.class);
		PublishOfficelist building=(PublishOfficelist) JSONObject.toBean(jsonObject,config);
		if(jsonObject.has("commonDataStatusByStatusId[id]")){
			building.setCommonDataStatusByStatusId(get(CommonDataStatus.class,jsonObject.getInt("commonDataStatusByStatusId[id]")));
		}
		if(jsonObject.has("commonDataDecoratedegreeByDecorateId[id]")){
			building.setCommonDataDecoratedegreeByDecorateId(get(CommonDataDecoratedegree.class,jsonObject.getInt("commonDataDecoratedegreeByDecorateId[id]")));
		}
		//commonDataDecoratedegreeByDecorateId
		if(jsonObject.has("publishOfficebuildinglistByOfficeBuildingId[id]")){
			building.setPublishOfficebuildinglistByOfficeBuildingId(get(PublishOfficebuildinglist.class,jsonObject.getInt("publishOfficebuildinglistByOfficeBuildingId[id]")));
		}
		building.setCreateTime(dateFormat.format(new Date()));
		saveOrUpdate(building);
		JSONObject returnObject=JSONObject.fromObject(building);
		return returnObject.toString();
	}

	public String load(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		PublishOfficelist building = load(PublishOfficelist.class, id);
		PublishOfficelist newBuilding=new PublishOfficelist();
		BeanUtils.copyProperties(newBuilding, building);
		PublishOfficebuildinglist officebuildinglist=new PublishOfficebuildinglist();
		officebuildinglist.setId(building.getPublishOfficebuildinglistByOfficeBuildingId().getId());
		officebuildinglist.setTitle(building.getPublishOfficebuildinglistByOfficeBuildingId().getTitle());
		newBuilding.setPublishOfficebuildinglistByOfficeBuildingId(officebuildinglist);
		return JSONObject.fromObject(newBuilding).toString();
	}

	public void delete(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(PublishOfficelist.class, id);
	}

	public void deleteByIds(JSONObject jsonObject) throws Exception {
		String ids=jsonObject.getString("ids");
		for(String id:ids.split(",")){
			if(id.equals("")){
				continue;
			}
			deleteById(PublishOfficelist.class, Integer.parseInt(id));
		}
	}

	public String loadPage(JSONObject jsonObject) throws Exception{
		System.out.println("jsonObject===="+jsonObject);
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		String hqlString="from PublishOfficelist ";
		String whereString="";
		String title=(String)jsonObject.get("title");
		int aredid=jsonObject.getInt("areaid");
		int subwayid=jsonObject.getInt("subwayid");
		int statusid=jsonObject.getInt("statusid");
		if(!StringUtils.isEmpty(title)){
			whereString=" title like '%"+title+"%' ";
		}
		if(aredid!=0){
			if(!StringUtils.isEmpty(whereString)){
				whereString=whereString+" and ";
			}
			whereString=whereString+" publishOfficebuildinglistByOfficeBuildingId.commonDataAreaByAreaTypeId.id="+aredid+" ";
		}
		if(subwayid!=0){
			if(!StringUtils.isEmpty(whereString)){
				whereString=whereString+" and ";
			}
			whereString=whereString+" publishOfficebuildinglistByOfficeBuildingId.commonDataSubwayBySubwayId.id="+subwayid+" ";
		}
		if(statusid!=0){
			if(!StringUtils.isEmpty(whereString)){
				whereString=whereString+" and ";
			}
			whereString=whereString+" commonDataStatusByStatusId.id="+statusid+" ";
		}
		if(!StringUtils.isEmpty(whereString)){
			whereString="where "+whereString;
		}
		Page page=findPageByFetchedHql(hqlString+whereString, null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return JSONObject.fromObject(page).toString();
	}
	
	public List loadAll(){
		String hqlString="from PublishOfficelist ";
		return getListByHQL(hqlString, null);
	}
}
