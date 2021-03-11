package com.qf.service;

import com.qf.pojo.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> finAll();

    Speaker findById(Speaker speaker);

    void update(Speaker speaker);

    void add(Speaker speaker);

    int deleteById(Speaker speaker);
}
