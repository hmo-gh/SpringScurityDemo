package com.example.springsecurity2.config;

import com.example.springsecurity2.entity.po.SysRole;
import com.example.springsecurity2.entity.po.SysUser;
import com.example.springsecurity2.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        SysUser user = userMapper.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("USER Not Found!!!!");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(SysRole role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }

}