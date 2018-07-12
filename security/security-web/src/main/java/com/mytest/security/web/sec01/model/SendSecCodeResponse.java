package com.mytest.security.web.sec01.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/3.
 */
@Data
public class SendSecCodeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String returnCode;

    private String message;

    private String value;
}
