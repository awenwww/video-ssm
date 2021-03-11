package com.qf.service;

import com.qf.pojo.User;

public interface UserService {
    User findByEmailAndPass(User user);

    int insertUser(User user);
    User validateEmail(User user);

    int update(User user1);

    int updateByEmail(User u);
    int repassByEmail(User user);
}
