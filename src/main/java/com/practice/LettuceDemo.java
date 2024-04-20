package com.practice;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.List;

/**
 * @author jason
 * @description
 * @create 2024/4/20 20:17
 **/
public class LettuceDemo {
    public static void main(String[] args) {
        //1. 使用構建器鏈式編成來build我們RedisURI
        RedisURI uri = RedisURI.builder()
                .redis("127.0.0.1")
                .withPort(6379)
                .withAuthentication("default", "111111")
                .build();

        //2. 創建客戶端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        //3. 通過connection創建操作的command
        RedisCommands<String, String> commands = connection.sync();

        //=======業務邏輯=======
        List<String> keys = commands.keys("*");
        System.out.println(keys);

        commands.set("k5", "hello-lettuce");
        System.out.println(commands.get("k5"));
        //=====================

        //4. 關閉資源
        connection.close();
        redisClient.shutdown();

    }
}
