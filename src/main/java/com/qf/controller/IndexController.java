package com.qf.controller;

import com.qf.pojo.Subject;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    SubjectService subjectService;
    @GetMapping("/index")
    public String index(Model model){
        List<Subject> all = subjectService.findAll();
        model.addAttribute("subjectList",all);
        return "/before/index.jsp";
    }
}
