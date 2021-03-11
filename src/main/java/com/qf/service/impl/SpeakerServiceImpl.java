package com.qf.service.impl;

import com.qf.dao.SpeakerDao;
import com.qf.pojo.Speaker;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    SpeakerDao speakerDao;
    @Override
    public List<Speaker> finAll() {
        return speakerDao.selectAll();
    }

    @Override
    public Speaker findById(Speaker speaker) {
        return speakerDao.selectOne(speaker);
    }

    @Override
    public void update(Speaker speaker) {
        Example example=new Example(Speaker.class);
        example.createCriteria().andEqualTo("id",speaker.getId());
        speakerDao.updateByExampleSelective(speaker,example);
    }

    @Override
    public void add(Speaker speaker) {
        speakerDao.insertSelective(speaker);
    }

    @Override
    public int deleteById(Speaker speaker) {
        return speakerDao.delete(speaker);
    }
}
