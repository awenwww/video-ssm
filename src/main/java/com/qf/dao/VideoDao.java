package com.qf.dao;

import com.qf.pojo.Video;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface VideoDao extends Mapper<Video> {
}
