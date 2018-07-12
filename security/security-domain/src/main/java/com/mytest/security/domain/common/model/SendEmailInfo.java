package com.mytest.security.domain.common.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/10/4.
 */
@Data
public class SendEmailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String secCode;

    private String ipAddr;

    private LocalDateTime sendDateTime;
}
