package com.mytest.security.web.sec01.controller;

import com.mytest.security.domain.common.Const;
import com.mytest.security.domain.common.bean.SendEmailInfoBean;
import com.mytest.security.domain.sec01.service.SEC01Service;
import com.mytest.security.web.common.message.Messages;
import com.mytest.security.web.sec01.model.SEC01Form;
import com.mytest.security.web.sec01.model.SendSecCodeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Administrator on 2017/9/7.
 */
@Controller
public class SEC01Controller {

    @Value("${email.max.length}")
    private String emailMaxLength;

    @Autowired
    @Qualifier("messageSource")
    MessageSource messageSource;

    @Autowired
    SEC01Service sec01Service;

    @Autowired
    SendEmailInfoBean sendEmailInfoBean;

    @Autowired
    HttpServletRequest request;


    @ModelAttribute
    public SEC01Form setupForm() {
        return new SEC01Form();
    }

    /**
     * FROM : NULL<br>
     * EVENT: 启动画面时的登录画面<br>
     * TO   : [SEC01SC01]<br>
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String def() {
        return "sec01/SEC01SC01";
    }

    /**
     * FROM : [SEC01SC01]<br>
     * EVENT: userName/email在DB里没找到或密码错误<br>
     * TO   : [SEC01SC01]<br>
     */
    @RequestMapping(value = "/sec01/username_not_found", method = RequestMethod.GET)
    public String usernameNotFound(Model model) {
        Messages messages = Messages.error().add("SEC01SC01.message.correct");
        model.addAttribute(messages);
        return "sec01/SEC01SC01";
    }

    /**
     * FROM : [SEC01SC01]<br>
     * EVENT: 登录成功后的主界面<br>
     * TO   : [SEC01SC03]<br>
     */
    @RequestMapping(value = "/sec01/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "sec01/SEC01SC03";
    }

    /**
     * FROM : [SEC01SC01]<br>
     * EVENT: 跳转到注册页面<br>
     * TO   : [SEC01SC02]<br>
     */
    @RequestMapping(value = "/sec01/sign_up", params = "init", method = RequestMethod.GET)
    public String signUpInit() {
        return "sec01/SEC01SC02";
    }

    /**
     * FROM : [SEC01SC02]<br>
     * EVENT: 按下[发送验证码]后生成验证码并发送邮箱<br>
     * TO   : [SEC01SC02]<br>
     */
    @RequestMapping(value = "/sec01/sign_up", params = "send_sec_code_ajax", method = RequestMethod.GET)
    @ResponseBody
    public SendSecCodeResponse sendSecCodeAjax(HttpServletRequest request, String email) {
        // 获取Locale对象
        Locale locale = LocaleContextHolder.getLocale();
        // Email的格式问题（电子邮箱不能为空且不大于XX位）
        if (StringUtils.isEmpty(email) || Integer.parseInt(emailMaxLength) < email.length()) {
            String msg = messageSource.getMessage("SEC01SC02.message.email.size", new Object[]{emailMaxLength},
                    locale);
            return setSendSecCodeResponse(Const.RETURN_ERROR_1, msg);
        }
        // Email是否已经被注册
        if (sec01Service.isRegistered(email)) {
            String msg = messageSource.getMessage("SEC01SC02.message.email.registered", null, locale);
            return setSendSecCodeResponse(Const.RETURN_ERROR_1, msg);
        }
        // Email是否在验证时间（例：3min）内再次验证，如果是，返回值会多一个“还剩XX秒可以再次验证”的时间（单位：秒）
        int seconds = sendEmailInfoBean.secondsBetweenSignUp(email);
        if (seconds != 0) {
            String msg = messageSource.getMessage("SEC01SC02.message.email.wait", null, locale);
            return setSendSecCodeResponse(Const.RETURN_ERROR_2, msg, String.valueOf(seconds));
        }

        // 发送验证码，把信息放入SendEmailInfoBean，同时更新SendEmailInfoBean的内容
        sec01Service.sendSecCode(email, request.getRemoteAddr());

        return setSendSecCodeResponse(Const.RETURN_SUCCESS,
                messageSource.getMessage("SEC01SC02.message.email.success", null, locale));
    }

    /**
     * FROM : [SEC01SC02]<br>
     * EVENT: 注册成功后跳转到登录页面(PRG模式)<br>
     * TO   : redirect:/sec01/sign_up?complete<br>
     */
    @RequestMapping(value = "/sec01/sign_up", method = RequestMethod.POST)
    public String signUp(@Validated(SEC01Form.SignUp.class) SEC01Form form, BindingResult result) {
        if (result.hasErrors()) {
            return "sec01/SEC01SC02";
        }
        sec01Service.saveSignUpUser(form.getEmail(), form.getPassword(), getIpAddr(request));

        return "redirect:/sec01/sign_up?complete";
    }

    /**
     * FROM : redirect:/sec01/sign_up?complete<br>
     * EVENT: [PRG]<br>
     * TO   : [SEC01SC01]<br>
     */
    @RequestMapping(value = "/sec01/sign_up", params = "complete", method = RequestMethod.GET)
    public String signUpComplete() {
        return "sec01/SEC01SC01";
    }

    private SendSecCodeResponse setSendSecCodeResponse(String returnCode, String message) {
        return setSendSecCodeResponse(returnCode, message, null);
    }

    private SendSecCodeResponse setSendSecCodeResponse(String returnCode, String message, String value) {
        SendSecCodeResponse result = new SendSecCodeResponse();
        result.setReturnCode(returnCode);
        result.setMessage(message);
        result.setValue(value);
        return result;
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
