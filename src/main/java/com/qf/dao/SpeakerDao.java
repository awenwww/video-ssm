package com.qf.dao;

import com.qf.pojo.Speaker;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface SpeakerDao extends Mapper<Speaker> {
}
