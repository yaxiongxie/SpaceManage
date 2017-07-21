package com.xyx.building.service;

import com.xyx.building.bean.PublishOfficebuildinglist;
import com.xyx.building.bean.PublishOfficelist;
import com.xyx.common.BaseService;
import com.xyx.common.Page;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component("OfficeService")
public class OfficeService extends BaseService {

	public String save(JSONObject jsonObject) throws Exception {
		PublishOfficelist building=(PublishOfficelist) JSONObject.toBean(jsonObject,PublishOfficelist.class);
		building.setCreateTime(new Timestamp(System.currentTimeMillis()));
		saveOrUpdate(building);
		JSONObject returnObject=JSONObject.fromObject(building);
		return returnObject.toString();
	}

	public String load(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		PublishOfficelist building = load(PublishOfficelist.class, id);
		PublishOfficelist newBuilding=new PublishOfficelist();
		BeanUtils.copyProperties(newBuilding, building);
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
		String hqlString="from PublishOfficelist ";
		return getListByHQL(hqlString, null);
	}
}
