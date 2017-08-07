package com.xyx;

import com.xyx.building.bean.PublishOfficebuildinglist;
import com.xyx.common.json.PropertyStrategyWrapper;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertySetStrategy;

import java.util.Iterator;

public class Test {

    public static void main(String[] args){
        String json="{'id':123,'titdle[id][sdf]':'ssdfsdf'}";
        JSONObject jsonObject=JSONObject.fromObject(json);
        jsonObject=filter(jsonObject);
        JsonConfig config = new JsonConfig();
        config.setPropertySetStrategy(new PropertyStrategyWrapper(PropertySetStrategy.DEFAULT));
        config.setRootClass(PublishOfficebuildinglist.class);
        PublishOfficebuildinglist building=(PublishOfficebuildinglist) JSONObject.toBean(jsonObject,config);
        System.out.println(building);
        System.out.println(building.getId());
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

class A{
    private int id;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
