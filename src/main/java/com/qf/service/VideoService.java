package com.qf.service;

import com.qf.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> findAll(Video video);
    void add(Video video);
    void delete(String  ids);

    Video findById(Video video);

    void update(Video video);
}
