package com.qf.service;

import com.qf.pojo.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();
    /**
    *
    * @Param: Subject
    * @return: 返回 一个subject 里面有多个 course 每个course里面又有多个  video
    * @Author: Mr.Wu
    * @Description:
    * @Date: 2021/3/6
    */
    Subject findById(Subject subject);
}
