package com.santeamo.firstboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/page")
public class ThymeleafController {

    @RequestMapping("/hello")
    public String hello(ModelMap modelMap){
        modelMap.addAttribute("msg","hello world, this is a thymeleaf page");
        return "hello";
    }

    @RequestMapping("/international")
    public String international(){

        return "international";
    }


    @RequestMapping("/internationalbylink")
    public String internationalbylink(){

        return "internationalbylink";
    }

}
