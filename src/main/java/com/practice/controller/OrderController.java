package com.practice.controller;

import com.practice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jason
 * @description
 * @create 2024/4/20 20:49
 **/
@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/order/add")
    public void addOrder() {
        orderService.addOrder();
    }

    @GetMapping("/order/{keyId}")
    public String getOrderById(@PathVariable("keyId") Integer keyId) {
        return orderService.getOrderById(keyId);
    }
}
