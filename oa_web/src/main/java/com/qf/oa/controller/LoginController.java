package com.qf.oa.controller;

import com.qf.oa.entity.Employee;
import com.qf.oa.service.IEmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @Author cx
 * @Time 2018/11/1 17:18
 * @Version 1.0
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private IEmpService empService;

    @RequestMapping("/login")
    public String login(String email, String password, Model model){
       // Employee employee = empService.login(email, password);

        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            try {
                subject.login(token);
            } catch (AuthenticationException ae) {
                model.addAttribute("error", 1);
                return "login";
            }
        }
        //登录成功
        Employee employee = (Employee) subject.getPrincipal();
        model.addAttribute("user", employee);
        return "index";
    }



    @RequestMapping("/toPage/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }
}
