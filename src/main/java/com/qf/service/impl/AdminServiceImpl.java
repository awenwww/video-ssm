package com.qf.service.impl;

import com.qf.dao.AdminDao;
import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    public List<Admin> findAll() {
        return adminDao.selectAll();
    }

    @Override
    public Admin findByNameAndPass(Admin admin) {
        return adminDao.selectOne(admin);
    }
}
