package com.example.springsecurity2.mapper;

import com.example.springsecurity2.entity.po.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    SysUser findByUsername(String username);

}
