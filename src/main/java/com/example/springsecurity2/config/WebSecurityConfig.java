package com.example.springsecurity2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

/*
 * Spring Security 配置类：
 * 重写它的方法来设置一些web安全的细节,如配置security的登录页面和传递的参数，公共路径权限属性等
 */

//标识该类是配置类
@Configuration

//开启security服务
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomUserDetailsService sysUserService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(sysUserService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    //HTTP请求安全处理
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers( "/toLogin").permitAll()
                .antMatchers("/user").hasRole("USER")
                //.hasIpAddress()//读取配置权限配置
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                //自定义登录界面
                .and().formLogin().loginPage("/toLogin").loginProcessingUrl("/login").failureUrl("/toLogin?error").permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().httpBasic()
                .and().sessionManagement().invalidSessionUrl("/toLogin")
                .and().csrf().disable();
        http.csrf().disable();
    }
}