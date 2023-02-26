package com.example.ymusic.Service.impl;

import com.example.ymusic.Mapper.UserMapper;
import com.example.ymusic.Service.UserService;
import com.example.ymusic.domain.LoginUser;
import com.example.ymusic.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Collections.emptyList;

/**
 * @author diyi
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userService.getUserMsgByAccount(account);
        if(user == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }else {
            log.info("User {}",user);
            log.info("用户登录成功：账号{} 密码{}",user.getEmail(),user.getPassword());
            userService.userLogin(user);
        }
        return new LoginUser(user);
    }
}
