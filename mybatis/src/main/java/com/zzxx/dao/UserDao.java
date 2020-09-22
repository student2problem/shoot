package com.zzxx.dao;

import com.zzxx.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    //增
    void saveUser(User user);
    //删
    void deleteById(int id);
    //根据id查
    User findById(int id);
    //查询总条数
    int findTotalCount();
    //改
    void updateUser(User user);
}
