package com.aaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 淮南King
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test(){
        return "test访问成功,这个不需要权限哦！";
    }


}
