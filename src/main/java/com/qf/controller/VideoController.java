package com.qf.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Course;
import com.qf.pojo.Speaker;
import com.qf.pojo.Subject;
import com.qf.pojo.Video;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.SubjectService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/afterVideo")
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    SpeakerService speakerService;
    @Autowired
    CourseService courseService;
    @Autowired
    SubjectService subjectService;
    @RequestMapping("/fuzzy")
    public String findVideoByFuzzyAndPage(Integer pageNum, Integer pageSize, Video video, Model model){
        pageNum= pageNum==null?1:pageNum;
        pageSize= pageSize==null?7:pageSize;

        PageHelper.startPage(pageNum,pageSize);

        List<Video> all = videoService.findAll(video);
        PageInfo<Video> pageInfo1=new PageInfo<>(all);

        model.addAttribute("page",pageInfo1);
        model.addAttribute("queryVo",video);

        List<Course> all1 = courseService.findAll();
        model.addAttribute("courseList",all1);
        List<Speaker> speakers = speakerService.finAll();
        model.addAttribute("speakerList",speakers);
        return "/behind/videoList.jsp";
    }
    @GetMapping("/toAdd")
    public String toVideoAdd(Video video,Model model){
        List<Course> all1 = courseService.findAll();
        model.addAttribute("courseList",all1);
        List<Speaker> speakers = speakerService.finAll();
        model.addAttribute("speakerList",speakers);
        if(video.getId()!=null){
            Video video1=videoService.findById(video);
            model.addAttribute("video",video1);
        }
        return "/behind/addVideo.jsp";
    }
    @PostMapping("/add")
    public String videoAdd(Video video){
        System.out.println(video);

        if(video.getId()!=null){
            videoService.update(video);
        }else{
            videoService.add(video);
        }
        return "redirect:../afterVideo/fuzzy";
    }
    @GetMapping("/delete")
    @ResponseBody
    public String videoDelete(String ids){
        videoService.delete(ids);
        return "success";
    }

    @PostMapping("/delete")
    public String videoDeleteAll(String ids){
        System.out.println(ids);
        videoService.delete(ids);

        return "redirect:../afterVideo/fuzzy";
    }
    @GetMapping("/showVideo")
    public String showVideo(Model model, Video video, Subject subject){
        //查询所有的课程类型
        List<Subject> all = subjectService.findAll();
        model.addAttribute("subjectList",all);
        //subjectName存
        model.addAttribute("subjectName",subject.getSubjectName());
        //根据video id 查询相关信息
        Video video1 = videoService.findById(video);
        Speaker speaker = new Speaker();
        speaker.setId(video1.getSpeakerId());
        video1.setSpeaker(speakerService.findById(speaker));
        Course course = new Course();
        course.setId(video1.getCourseId());
        Course course1 = courseService.findById(course);

        video1.setCourse(course1);
        System.out.println(video1);
        model.addAttribute("video",video1);

        return "/before/section.jsp";
    }
    @PostMapping("/updatePlayNum")
    public void updatePlayNum(Video video){
        Integer playNum = video.getPlayNum();
        video.setPlayNum(playNum+1);
        System.out.println(video);
        videoService.update(video);
    }

}
