package com.example.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

/**
 * @Description:
 * @Author: guozongchao
 * @Date: 2020/7/24 15:38
 */
@Controller
public class RouterController {
    @GetMapping({"/", "/index"})
    public String toIndex() {
        return "index";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/toLogout")
    public void toLogout() {
        System.out.println("注销");
    }

    @GetMapping("/v1/{id}")
    public String toV1View(@PathVariable String id) {
        return "v1/view"+id;
    }
    @GetMapping("/v2/{id}")
    public String toV2View(@PathVariable String id) {
        return "v2/view"+id;
    }
    @GetMapping("/v3/{id}")
    public String toV3View(@PathVariable String id) {
        return "v3/view"+id;
    }
}
