package com.mytest.security.web.common.controlleradvice;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Created by Administrator on 2017/9/20.
 */
@ControllerAdvice
public class SECControllerAdvice {

    /**
     * 把从画面传来的空字符串转换成NULL
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
