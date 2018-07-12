package com.mytest.security.domain.sec01.serviceimpl;


import com.mytest.security.domain.common.Const;
import com.mytest.security.domain.common.bean.SendEmailInfoBean;
import com.mytest.security.domain.common.model.SendEmailInfo;
import com.mytest.security.domain.common.service.SendEmailService;
import com.mytest.security.domain.common.utils.SecUtils;
import com.mytest.security.domain.entity.User;
import com.mytest.security.domain.repository.UserRepository;
import com.mytest.security.domain.sec01.service.SEC01Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/8/30.
 */
@Transactional
@Service
public class SEC01ServiceImpl implements SEC01Service {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SendEmailService sendEmailService;

    @Autowired
    SendEmailInfoBean sendEmailInfoBean;

    @Override
    public User findUser(String accountName) {

        User user;
        if (StringUtils.isNumeric(accountName)) {
            user = userRepository.findOneByUserId(Integer.valueOf(accountName), Const.NOT_DELETED);
        } else {
            user = userRepository.findOneByEmail(accountName, Const.NOT_DELETED);
        }

        return user;
    }

    @Override
    public boolean isRegistered(String email) {
        return userRepository.findOneByEmail(email, Const.NOT_DELETED) == null ? false : true;
    }

    @Override
    public void sendSecCode(String email, String ipaddr) {

        // 获取一个4位的验证码
        String secCode = SecUtils.getSecurityCode(4);
        LocalDateTime now = LocalDateTime.now();

        // 发送邮件
        sendEmailService.sendSecCodeMail(email, secCode);

        // 把信息放入SendEmailInfoBean
        SendEmailInfo info = new SendEmailInfo();
        info.setEmail(email);
        info.setSecCode(secCode);
        info.setIpAddr(ipaddr);
        info.setSendDateTime(now);
        sendEmailInfoBean.updateInfo(info);

        // 更新SendEmailInfoBean的内容
        sendEmailInfoBean.updateInfo(true);
    }

    @Override
    public void saveSignUpUser(String email, String password, String ipaddr) {

        // 对密码编码
        String encodePassword = encoder.encode(password);
        // 获取当前日期
        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodePassword);
        user.setRoleId(Const.ROLE_COMMON_USER);
        user.setLastLoginDatetime(now);
        user.setLastLoginIpAddress(ipaddr);
        user.setIsDelete(Boolean.FALSE);
        user.setCreateDatetime(now);
        user.setCreateUser("0");
        user.setUpdateDatetime(now);
        user.setUpdateUser("0");
    }
}
