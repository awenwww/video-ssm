package com.qf.service.impl;

import com.qf.dao.CourseDao;
import com.qf.dao.SpeakerDao;
import com.qf.dao.VideoDao;
import com.qf.pojo.Course;
import com.qf.pojo.Speaker;
import com.qf.pojo.Video;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    VideoDao videoDao;
    @Autowired
    SpeakerDao speakerDao;
    @Override
    public List<Course> findAll() {
        return courseDao.selectAll();
    }

    @Override
    public Course findById(Course course) {
        System.out.println(course);
        Course course1 = courseDao.selectOne(course);
        Example example=new Example(Video.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("courseId",course.getId());
        List<Video> videoList = videoDao.selectByExample(example);
        for (int i = 0; i < videoList.size(); i++) {
            Video video = videoList.get(i);
            Speaker speaker = new Speaker();
            speaker.setId(video.getSpeakerId());
            Speaker speaker1 = speakerDao.selectOne(speaker);
            videoList.get(i).setSpeaker(speaker1);
        }
        course1.setVideoList(videoList);
        return course1;
    }
}
