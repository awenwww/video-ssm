package com.qf.controller;

import com.qf.pojo.Subject;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @GetMapping("/findById")
    public String findById(Subject subject, Model model){
        List<Subject> all = subjectService.findAll();
        model.addAttribute("subjectList",all);
        Subject subject1 = subjectService.findById(subject);
        model.addAttribute("subject",subject1);
        return "/before/course.jsp";
    }
}
