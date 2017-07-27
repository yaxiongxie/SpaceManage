package com.xyx;

import com.xyx.building.bean.PublishOfficebuildinglist;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class Test {

    public static void main(String[] args){
        String json="{'id':123,'title':'ssdfsdf'}";
        JsonConfig config = new JsonConfig();
        config.setIgnoreDefaultExcludes(false);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.setExcludes(new String[]{//只要设置这个数组，指定过滤哪些字段。
                "title"
        });
        JSONObject jsonObject=JSONObject.fromObject(json,config);
        System.out.println(jsonObject);
        PublishOfficebuildinglist building= (PublishOfficebuildinglist) JSONObject.toBean(jsonObject,PublishOfficebuildinglist.class);
        System.out.println(building.getId());
    }
}
