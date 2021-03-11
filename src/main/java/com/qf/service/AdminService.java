package com.qf.service;

import com.qf.pojo.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();
    Admin findByNameAndPass(Admin admin);
}
