package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Speaker;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/afterSpeaker")
public class SpeakerController {
    @Autowired
    SpeakerService speakerService;
    @GetMapping("/fuzzy")
    public String speakerFuzzy(Integer pageNum, Integer pageSize, Model model){
        pageNum= pageNum==null?1:pageNum;
        pageSize= pageSize==null?5:pageSize;

        PageHelper.startPage(pageNum,pageSize);
        List<Speaker> speakers = speakerService.finAll();
        PageInfo<Speaker> pageInfo=new PageInfo<>(speakers);
        model.addAttribute("page",pageInfo);
        return "/behind/speakerList.jsp";
    }
    @GetMapping("/toAddOrEdit")
    public String toAddOrEdit(Speaker speaker,Model model){
        if(speaker.getId()!=null){
            Speaker speaker1=speakerService.findById(speaker);
            model.addAttribute("speaker",speaker1);
        }
        return "/behind/addSpeaker.jsp";
    }
    @PostMapping("/addOrEdit")
    public String addOrEdit(Speaker speaker){
        if(speaker.getId()!=null){
            speakerService.update(speaker);
        }else{
            speakerService.add(speaker);
        }
        return "redirect:../afterSpeaker/fuzzy";
    }
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Speaker speaker){
        int count=speakerService.deleteById(speaker);
        if(count>=1){
            return "success";
        }else {
            return "failed";
        }
    }


}
