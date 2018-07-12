package com.mytest.security.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/16.
 * 【MASTER】
 */
@Data
public class Function implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer functionId;
    private String functionName;
    private String description;
    private Boolean isDelete;
    private LocalDateTime createDatetime;
    private String createUser;
    private LocalDateTime updateDatetime;
    private String updateUser;
}
