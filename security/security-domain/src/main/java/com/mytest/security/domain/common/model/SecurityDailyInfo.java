package com.mytest.security.domain.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/2.
 */
@Data
public class SecurityDailyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String securityCode;

    private String day;

    private String open;

    private String high;

    private String low;

    private String close;

    private String volume;
}
