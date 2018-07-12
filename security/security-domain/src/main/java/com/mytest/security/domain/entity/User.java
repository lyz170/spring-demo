package com.mytest.security.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/8/21.
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String email;
    private String password;
    private Integer roleId;
    private String userName;
    private String realName;
    private String gender;
    private LocalDate birthday;
    private String telephone;
    private LocalDateTime lastLoginDatetime;
    private String lastLoginIpAddress;
    private Boolean isDelete;
    private LocalDateTime createDatetime;
    private String createUser;
    private LocalDateTime updateDatetime;
    private String updateUser;
}
