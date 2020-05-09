package com.santeamo.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @RequestMapping("/error")
    public String error(){
        Integer integer = Integer.parseInt("");
        return "error";
    }

    @RequestMapping("/ajaxerror")
    public String ajaxerror(){
        return "ajaxerror";
    }

    @RequestMapping("/getAjaxerror")
    @ResponseBody
    public Object getAjaxerror() {

        int a = 1 / 0;

        return "";
    }

}
