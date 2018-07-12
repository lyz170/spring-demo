package com.mytest.security.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/11/4.
 * 【MASTER】
 */
@Data
public class Security implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer functionId;
    private String securityType;
    private String regionType;
    private String industryType;
    private String securityShortName;
    private LocalDate listingDate;
    private LocalDate terminationDate;
    private String generalCapital;
    private String negotiableCapital;
    private Boolean isDelete;
    private LocalDateTime createDatetime;
    private String createUser;
    private LocalDateTime updateDatetime;
    private String updateUser;
}
