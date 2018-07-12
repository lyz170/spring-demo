package com.mytest.security.web.sec01.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/19.
 */
@Data
public class SEC01Form implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface SignUp {
    }

    private String accountName;

    @NotNull(groups = {SignUp.class})
    @Max(groups = {SignUp.class}, value = 32)
    private String email;

    @NotNull(groups = {SignUp.class})
    private String securityCode;

    @NotNull(groups = {SignUp.class})
    @Size(groups = {SignUp.class}, min = 8, max = 20)
    private String password;

    @NotNull(groups = {SignUp.class})
    @Size(groups = {SignUp.class}, min = 8, max = 20)
    private String passwordConfirm;
}
