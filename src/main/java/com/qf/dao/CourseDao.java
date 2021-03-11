package com.qf.dao;

import com.qf.pojo.Course;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface CourseDao extends Mapper<Course> {
}
