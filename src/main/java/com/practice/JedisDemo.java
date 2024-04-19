package com.practice;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jason
 * @description
 * @create 2024/4/19 17:56
 **/
public class JedisDemo {
    public static void main(String[] args) {
        //1. connection獲得，通過指定IP和端口號
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //2. 指定訪問服務器的密碼
        jedis.auth("111111");

        //3. 獲得了jedis客戶端，可以向jdbc一樣，訪問redis
        System.out.println(jedis.ping());

        //keys
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        //string
        jedis.set("k3", "hello-jedis");
        System.out.println(jedis.get("k3"));
        System.out.println("ttl=" + jedis.ttl("k3"));

        //list
        jedis.lpush("list", "11", "12", "13");
        List<String> list = jedis.lrange("list", 0, -1);//全部
        for (String s : list) {
            System.out.println(s);
        }

        //hash
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "zhangsan");
        map.put("age", "25");
        jedis.hset("user:001", map);
        System.out.println("jedis.hgetAll(\"user:001\") = " + jedis.hgetAll("user:001"));

        //set
        jedis.sadd("set", "1,2,3,4,5");
        System.out.println("jedis.smembers(\"set\") = " + jedis.smembers("set"));

        //zset
        jedis.zadd("zset",100,"zhangsan");
        System.out.println("jedis.zrange(\"zet\",0,-1) = " + jedis.zrange("zset", 0, -1));
    }
}
