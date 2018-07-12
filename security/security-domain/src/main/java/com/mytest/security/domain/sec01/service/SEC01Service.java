package com.mytest.security.domain.sec01.service;

import com.mytest.security.domain.entity.User;

/**
 * Created by Administrator on 2017/8/30.
 */
public interface SEC01Service {

    /**
     * 登录时按邮箱/用户ID查找User,供UserDetailService使用<br>
     */
    User findUser(String accountName);

    /**
     * 邮箱是否已经被注册<br>
     */
    boolean isRegistered(String email);

    /**
     * 向邮箱发送验证码<br>
     */
    void sendSecCode(String email, String ipaddr);

    /**
     * 保存添加注册用户信息<br>
     */
    void saveSignUpUser(String email, String password, String ipaddr);
}
