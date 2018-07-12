package com.mytest.security.domain.sec01.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/28.
 */
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String email;

    private String password;
}
