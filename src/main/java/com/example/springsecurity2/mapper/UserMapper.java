package com.example.springsecurity2.mapper;

import com.example.springsecurity2.entity.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findByUsername(String username);

}
