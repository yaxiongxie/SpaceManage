package com.xyx.building.service;

import com.xyx.building.bean.PublishOfficebuildinglist;
import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.bean.*;
import com.xyx.common.json.PropertyStrategyWrapper;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
import net.sf.json.util.PropertySetStrategy;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component("BuildingService")
public class BuildingService extends BaseService {

	public String save(JSONObject jsonObject) throws Exception {
		jsonObject=filter(jsonObject);
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonConfig config = new JsonConfig();
		config.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
		config.setRootClass(PublishOfficebuildinglist.class);
		PublishOfficebuildinglist building=(PublishOfficebuildinglist) JSONObject.toBean(jsonObject,config);
		if(jsonObject.has("commonDataAreaByAreaTypeId[id]")){
			building.setCommonDataAreaByAreaTypeId(get(CommonDataArea.class,jsonObject.getInt("commonDataAreaByAreaTypeId[id]")));
		}
		if(jsonObject.has("commonDataDistrictByDistrictTypeId[id]")){
			building.setCommonDataDistrictByDistrictTypeId(get(CommonDataDistrict.class,jsonObject.getInt("commonDataDistrictByDistrictTypeId[id]")));
		}
		if(jsonObject.has("commonDataSubwayBySubwayId[id]")){
			building.setCommonDataSubwayBySubwayId(get(CommonDataSubway.class,jsonObject.getInt("commonDataSubwayBySubwayId[id]")));
		}
		if(building.getBuildTime()==null || building.getBuildTime().trim().equals("")){
			building.setBuildTime(null);
		}
		building.setCommonDataSourcetypeByBuildingTypeId(get(CommonDataSourcetype.class,1));
		building.setCommonDataStatusByStatusId(get(CommonDataStatus.class,1));
		building.setCreateTime(dateFormat.format(new Date()));
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
		int aredid=jsonObject.getInt("areaid");
		int subwayid=jsonObject.getInt("subwayid");
		if(!StringUtils.isEmpty(title)){
			whereString=" title like '%"+title+"%' ";
		}
		if(aredid!=0){
			if(!StringUtils.isEmpty(whereString)){
				whereString=whereString+" and ";
			}
			whereString=whereString+" commonDataAreaByAreaTypeId.id="+aredid+" ";
		}
		if(subwayid!=0){
			if(!StringUtils.isEmpty(whereString)){
				whereString=whereString+" and ";
			}
			whereString=whereString+" commonDataSubwayBySubwayId.id="+subwayid+" ";
		}
		if(!StringUtils.isEmpty(whereString)){
			whereString="where "+whereString;
		}
		Page page=findPageByFetchedHql(hqlString+whereString+" order by orderInt desc,id", null, pageNo, pageSize, new Object[]{});
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
	public List loadAllDecorate(){
		String hqlString="from CommonDataDecoratedegree ";
		return getListByHQL(hqlString, null);
	}

	public static JSONObject filter(JSONObject jsonObject){
		Iterator iterator=jsonObject.keys();
		JSONObject returnObject=JSONObject.fromObject(jsonObject.toString());
		while(iterator.hasNext()){
			String key=iterator.next().toString();
			int subCount=subCount(key,"[");
			if(subCount>1){
				returnObject.remove(key);
			}
			System.out.println(key+subCount(key,"["));
		}

		return returnObject;
	}

	public static int subCount(String str,String substr)
	{
		int index=0;
		int count=0;
		int fromindex=0;
		while((index=str.indexOf(substr,fromindex))!=-1)
		{
			//str=str.substring(index+substr.length());
			fromindex=index+substr.length();
			count++;
		}
		return count;
	}
}
