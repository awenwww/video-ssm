package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public User findByEmailAndPass(User user) {
        return userDao.findByEmailAndPass(user);
    }

    @Override
    public int insertUser(User user) {

        return userDao.insertUser(user);
    }

    @Override
    public User validateEmail(User user) {
        return userDao.validateEmail(user);
    }

    @Override
    public int update(User user1) {
        return userDao.update(user1);
    }

    @Override
    public int updateByEmail(User u) {
        return userDao.updateByEmail(u);
    }

    @Override
    public int repassByEmail(User user) {
        return userDao.repassByEmail(user);
    }
}
