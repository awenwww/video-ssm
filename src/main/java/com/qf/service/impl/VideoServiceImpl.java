package com.qf.service.impl;

import com.qf.dao.CourseDao;
import com.qf.dao.SpeakerDao;
import com.qf.dao.VideoDao;
import com.qf.pojo.Course;
import com.qf.pojo.Speaker;
import com.qf.pojo.Video;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoDao videoDao;
    @Autowired
    SpeakerDao speakerDao;
    @Autowired
    CourseDao courseDao;
    @Override
    public List<Video> findAll(Video video1) {

        Example example1=new Example(Video.class);
        Example.Criteria criteria = example1.createCriteria();
        if (video1.getTitle()!=null){
            criteria.andLike("title","%"+video1.getTitle()+"%");
        }
        if(video1.getSpeakerId()!=null){
            criteria.andEqualTo("speakerId",video1.getSpeakerId());
        }
        if (video1.getCourseId()!=null){
            criteria.andEqualTo("courseId",video1.getCourseId());
        }

        List<Video> videos = videoDao.selectByExample(example1);
        for (int i = 0; i < videos.size(); i++) {
            Video video = videos.get(i);
            Integer speakerId = video.getSpeakerId();
            /*根据speakerId查询出来speak封装进去*/
            Example example=new Example(Speaker.class);
            example.createCriteria().andEqualTo("id",speakerId);
            List<Speaker> speakers = speakerDao.selectByExample(example);
           // System.out.println(speakers.get(0));
            video.setSpeaker(speakers.get(0));

            /*根据courseId查询出来course封装进去*/
            Course course = new Course();
            course.setId(video.getCourseId());
            Course course1 = courseDao.selectOne(course);
            video.setCourse(course1);


            /*Speaker speaker = speakerDao.selectByPrimaryKey(7);
            System.out.println(speaker);
            Speaker t=new Speaker();
            t.setId(8);
            Speaker speaker1 = speakerDao.selectOne(t);
            System.out.println(speaker1);*/
        }
        return videos;
    }

    @Override
    public void add(Video video) {
        videoDao.insertSelective(video);
    }

    @Override
    public void delete(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Video video = new Video();
            video.setId(Integer.parseInt(s));
            videoDao.delete(video);
        }
    }

    @Override
    public Video findById(Video video) {
        return videoDao.selectOne(video);
    }

    @Override
    public void update(Video video) {
        Example example=new Example(Video.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",video.getId());
        videoDao.updateByExampleSelective(video,example);
    }
}
