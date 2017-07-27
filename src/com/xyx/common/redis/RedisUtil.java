package com.xyx.common.redis;

import redis.clients.jedis.Jedis;

public class RedisUtil {

    public static void setValue(String key,String value){
        Jedis jedis=new Jedis("118.31.46.32");
        jedis.auth("Xieyaxiong9");
        jedis.set(key,value);
        jedis.disconnect();

    }

    public static String getValue(String key){
        Jedis jedis=new Jedis("118.31.46.32");
        jedis.auth("Xieyaxiong9");
        String value=jedis.get(key);
        jedis.disconnect();
        return value;

    }

    public static void main(String[] args){
        setValue("filePath","/var/local/pic/pic_folder/");
        System.out.println("ss");
    }
}
