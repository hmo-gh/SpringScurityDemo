package com.example.springsecurity2.config;

import com.example.springsecurity2.entity.po.User;
import com.example.springsecurity2.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/*
 *1）首先需要自定义 UserDetailsService,将用户信息和权限注入进来
 * 2）需要重写loadUserByUsername方法，参数的是用户输入的名字。返回值是 UserDetails，这是一个接口，一般使用它的
 *    子类 org.springframework.security.core.userdetails.User，它有三个参数，分别是用户名，密码和权限集
 * 3）实际情况下，大多将 DAO 中的 User 类继承 org.springframework.security.core.userdetails.User 返回。
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userMapper.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User Not Found");
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (StringUtils.isNotBlank(user.getRoles())) {
                String[] roles = user.getRoles().split(",");
                for (String role : roles) {
                    if (StringUtils.isNotBlank(role)) {
                        authorities.add(new SimpleGrantedAuthority(role.trim()));
                    }
                }
            }return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}