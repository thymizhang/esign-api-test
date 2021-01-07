package com.esign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author thymi
 * @Date 2020/12/30
 */
@RestController
public class EsignController {

    @GetMapping("/start")
    public String start(){
        return "e签宝接口测试";
    }
}
