package com.practice.controller;

import com.practice.service.HyperLogLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jason
 * @description
 * @create 2024/5/20 21:44
 **/
@RestController
public class HyperLogLogController {
    @Resource
    private HyperLogLogService hyperLogLogService;

    @GetMapping("/uv")
    public long uv() {
        return hyperLogLogService.uv();
    }
}
