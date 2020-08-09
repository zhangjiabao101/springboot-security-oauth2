package com.aaa.config;

import com.aaa.dao.UserDao;
import com.aaa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现spring-security核心接口UserDetailsService
 * 负载用户特定数据
 *
 * @author 淮南King
 */
@Service
public class UserDetail implements UserDetailsService {

    @Autowired UserDao userDao;

    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     */
    @Override public UserDetails loadUserByUsername(String username) {
        //将来连接数据库根据账号查询用户信息
        User user = userDao.getUserByUsername(username);
        //当查询此用户不存在时，将抛出用户名未找到异常
        if (user == null) {
            throw new UsernameNotFoundException("No such user found, the user name is: "+username);
        }
        //根据用户id查询权限
        List<String> permissions = userDao.findPermissionsByUserId(user.getId());
        //将permissions转为数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //创建UserDetails 将从数据库查询到的用户信息包装返回给Security
        UserDetails userDetails =
            org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities(permissionArray)
                .build();
        return userDetails;
    }
}
