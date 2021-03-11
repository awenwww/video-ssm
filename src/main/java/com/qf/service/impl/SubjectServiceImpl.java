package com.qf.service.impl;

import com.qf.dao.CourseDao;
import com.qf.dao.SubjectDao;
import com.qf.dao.VideoDao;
import com.qf.pojo.Course;
import com.qf.pojo.Subject;
import com.qf.pojo.Video;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    VideoDao videoDao;
    @Autowired
    CourseDao courseDao;
    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = subjectDao.selectAll();
        return subjects;
    }

    @Override
    public Subject findById(Subject subject) {
        //查询到 subject
        Subject subject1 = subjectDao.selectOne(subject);
        //根据subject 查course
        Course course = new Course();
        course.setSubjectId(subject1.getId());
        List<Course> courseList = courseDao.select(course);
        for (int i = 0; i <courseList.size() ; i++) {
            //然后根据 courseId 查 video
            Video video=new Video();
            video.setCourseId(courseList.get(i).getId());
            List<Video> videoList = videoDao.select(video);
            courseList.get(i).setVideoList(videoList);
        }
        subject1.setCourseList(courseList);

        return subject1;
    }
}
