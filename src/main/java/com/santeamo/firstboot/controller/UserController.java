package com.santeamo.firstboot.controller;

import com.santeamo.firstboot.model.User;
import com.santeamo.firstboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUser")
    public User getUser() {
        User user=new User();
        user.setUserName("小明");
        user.setPassWord("xxxx");
        return user;
    }

    @RequestMapping("/redisTest")
    public String redisTest(){


        return "";
    }

    @RequestMapping("/userlist")
    public String userList(Model model){

        List<User> userList = userRepository.findAll();

        model.addAttribute("tabletitle","用户列表");
        model.addAttribute("list",userList);

        return "userlist";
    }

}
