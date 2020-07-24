package com.example.springsecuritydemo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:
 * @Author: guozongchao
 * @Date: 2020/7/24 15:48
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //首页不需要授权
                .antMatchers("/", "/index").permitAll()
                .and()
                /* 对指定请求链接设置授权，需要响应的角色权限才能访问 */
                .authorizeRequests().antMatchers("/v1/**").hasRole("vip1")
                .and()
                .authorizeRequests().antMatchers("/v2/**").hasRole("vip2")
                .and()
                .authorizeRequests().antMatchers("/v3/**").hasRole("vip3");

        //设置登录表单
        http.formLogin()
                //不采用默认的登录页面，设置自定义登录页面
                .loginPage("/toLogin")
                //设置登录请求参数字段
                .usernameParameter("userName")
                .passwordParameter("password")
                //设置登录处理请求
                .loginProcessingUrl("/login");

        //注销
        http.logout().deleteCookies("remove").invalidateHttpSession(true);
    }

    /**
     * 认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //应该通过数据库进行认证用户
        //这里只通过写死用户角色存储在内存中认证
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip3")
                .and()
                .withUser("gzc").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2");
    }

}
