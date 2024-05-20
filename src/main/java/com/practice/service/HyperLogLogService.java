package com.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author jason
 * @description
 * @create 2024/5/20 21:44
 **/
@Service
@Slf4j
public class HyperLogLogService {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 模擬後台有用戶點擊首頁，每個用戶來自不同IP
     */
    @PostConstruct
    public void initIp() {
        new Thread(() -> {
            String ip = null; //255.255.255.255
            for (int i = 0; i < 200; i++) {
                Random random = new Random();
                ip = random.nextInt(256) + "."
                        + random.nextInt(256) + "."
                        + random.nextInt(256) + "."
                        + random.nextInt(256);

                Long hll = redisTemplate.opsForHyperLogLog().add("hll", ip);
                log.info("ip={}, 該IP地址訪問首頁的次數={}", ip, hll);
                //暫停3s
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }, "t1").start();
    }

    public long uv() {
        //PFCOUNT
        return redisTemplate.opsForHyperLogLog().size("hll");
    }
}
