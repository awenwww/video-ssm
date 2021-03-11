package com.qf.dao;

import com.qf.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserDao extends Mapper<User> {
   User findByEmailAndPass(User user);

    int insertUser(User user);

    User validateEmail(User user);

    int update(User user1);

    int updateByEmail(User u);
    int repassByEmail(User user);
}
