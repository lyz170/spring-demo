package com.mytest.security.domain.common.authentication;

import com.mytest.security.domain.entity.User;
import com.mytest.security.domain.sec01.service.SEC01Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */
public class SecUserServiceImpl implements UserDetailsService {

    @Autowired
    SEC01Service sce01Serivce;

    @Override
    public UserDetails loadUserByUsername(String s) {

        User user = sce01Serivce.findUser(s);
        if (user == null) {
            throw new UsernameNotFoundException("The given account is not found! accountName = " + s);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                StringUtils.isEmpty(user.getUserName()) ? user.getUserId().toString() : user.getUserName(),
                user.getPassword(), createAuthority());

        return userDetails;
    }

    /**
     * 创建权限List(TODO)
     */
    private List<SimpleGrantedAuthority> createAuthority() {

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SEC01");
        authorityList.add(authority);

        return authorityList;
    }
}
