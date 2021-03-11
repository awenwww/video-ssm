package com.qf.controller;

import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/afterAdmin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Admin> findAll(){
        return adminService.findAll();
    }
    @GetMapping("/toLogin")
    public String toLogin(HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin!=null){
            return "redirect:../afterVideo/fuzzy";
        }else{
            return "/behind/login.jsp";
        }

    }
    @PostMapping("/login")
    @ResponseBody
    public String login(Admin admin, HttpSession session){
        Admin admin1 = adminService.findByNameAndPass(admin);
        if(admin1!=null){
            session.setAttribute("admin",admin1);
            return "success";
        }else{
            return "failed";
        }
    }
    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:../afterAdmin/toLogin";
    }
}
