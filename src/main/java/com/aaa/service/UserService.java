package com.aaa.service;

import com.aaa.dao.UserDao;
import com.aaa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息持久业务层
 *
 * @author 淮南King
 * @date 2020-07-21
 */
@Service
public class UserService {

    @Autowired UserDao dao;

    public User getUserByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    public List<String> findPermissionsByUserId(String userId) {
        return dao.findPermissionsByUserId(userId);
    }
}
