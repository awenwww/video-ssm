package com.qf.service;

import com.qf.pojo.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Course course);
}
