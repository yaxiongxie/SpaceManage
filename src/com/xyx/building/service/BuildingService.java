package com.xyx.building.service;

import com.xyx.building.bean.PublishOfficebuildinglist;
import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.bean.*;
import com.xyx.common.encrypt.MD5;
import com.xyx.core.bean.CorePerson;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("BuildingService")
public class BuildingService extends BaseService {

	public String save(JSONObject jsonObject) throws Exception {
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PublishOfficebuildinglist building=(PublishOfficebuildinglist) JSONObject.toBean(jsonObject,PublishOfficebuildinglist.class);
		if(jsonObject.has("commonDataAreaByAreaTypeId")){
			building.setCommonDataAreaByAreaTypeId(get(CommonDataArea.class,jsonObject.getInt("commonDataAreaByAreaTypeId")));
		}
		if(jsonObject.has("commonDataDistrictByDistrictTypeId")){
			building.setCommonDataDistrictByDistrictTypeId(get(CommonDataDistrict.class,jsonObject.getInt("commonDataDistrictByDistrictTypeId")));
		}
		if(jsonObject.has("commonDataSubwayBySubwayId")){
			building.setCommonDataSubwayBySubwayId(get(CommonDataSubway.class,jsonObject.getInt("commonDataSubwayBySubwayId")));
		}
		building.setCommonDataSourcetypeByBuildingTypeId(get(CommonDataSourcetype.class,1));
		building.setCommonDataStatusByStatusId(get(CommonDataStatus.class,1));
		building.setCreateTime(new Timestamp(System.currentTimeMillis()));
		building.setBuildTime(dateFormat.format(new Date(jsonObject.getLong("buildTime"))));
		saveOrUpdate(building);
		JSONObject returnObject=JSONObject.fromObject(building);
		return returnObject.toString();
	}

	public String load(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		PublishOfficebuildinglist building = load(PublishOfficebuildinglist.class, id);
		PublishOfficebuildinglist newBuilding=new PublishOfficebuildinglist();
		BeanUtils.copyProperties(newBuilding, building);
		return JSONObject.fromObject(newBuilding).toString();
	}

	public void delete(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(PublishOfficebuildinglist.class, id);
	}

	public void deleteByIds(JSONObject jsonObject) throws Exception {
		String ids=jsonObject.getString("ids");
		for(String id:ids.split(",")){
			if(id.equals("")){
				continue;
			}
			deleteById(PublishOfficebuildinglist.class, Integer.parseInt(id));
		}
	}

	public String loadPage(JSONObject jsonObject) throws Exception{
		System.out.println("jsonObject===="+jsonObject);
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		String hqlString="from PublishOfficebuildinglist ";
		String whereString="";
		String title=(String)jsonObject.get("title");
		if(!StringUtils.isEmpty(title)){
			whereString=" title like '%"+title+"%' ";
		}
		if(!StringUtils.isEmpty(whereString)){
			whereString="where "+whereString;
		}
		Page page=findPageByFetchedHql(hqlString+whereString, null, pageNo, pageSize, new Object[]{});
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return JSONObject.fromObject(page).toString();
	}
	
	public List loadAll(){
		String hqlString="from PublishOfficebuildinglist ";
		return getListByHQL(hqlString, null);
	}

	public List loadAllArea(){
		String hqlString="from CommonDataArea ";
		return getListByHQL(hqlString, null);
	}
	public List loadDistrictById(int id){
		if(id==-1) {
			String hqlString = "from CommonDataDistrict ";
			return getListByHQL(hqlString, null);
		}else{
			String hqlString = "from CommonDataDistrict where commonDataAreaByAreaTypeId.id=? ";
			List list= getListByHQL(hqlString, id);
			System.out.println("size====>>>>"+list.size());
			return list;
		}
	}
	public List loadAllSubway(){
		String hqlString="from CommonDataSubway ";
		return getListByHQL(hqlString, null);
	}
	public List loadAllStatus(){
		String hqlString="from CommonDataStatus ";
		return getListByHQL(hqlString, null);
	}
}
