package com.mytest.security.domain.common.bean;

import com.mytest.security.domain.common.model.SendEmailInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/4.
 * 该类的主要作用：<br/>
 * (1) 存放3分钟内有效的验证码信息<br/>
 * (2) 验证是否在3分钟内同一email多次注册(还剩XX秒可以再次验证)<br/>
 */

@Component
public class SendEmailInfoBean implements Serializable {

    @Value("${seccode.verify.minutes}")
    private String verifyMinutes;

    private List<SendEmailInfo> sendEmailInfoList = new LinkedList<>();

    /**
     * 存放3分钟内有效的验证码信息：<br/>
     * (a) 如果没有，则添加；如果有，则替换<br/>
     * (b) 清空所有超时的 [TODO]可以放在JOB里执行<br/>
     */
    public void updateInfo(SendEmailInfo param) {
        updateInfo(param, false);
    }

    public void updateInfo(boolean needClear) {
        updateInfo(null, true);
    }

    private synchronized void updateInfo(SendEmailInfo param, boolean needClear) {
        LocalDateTime now = LocalDateTime.now();
        long verifySeconds = Long.parseLong(verifyMinutes) * 60;
        if (needClear) {
            sendEmailInfoList.forEach(p -> {
                if (verifySeconds < getRemainingSeconds(p.getSendDateTime(), now)) {
                    sendEmailInfoList.remove(p);
                }
            });
        } else {
            boolean isReplace = false;
            for (SendEmailInfo elem : sendEmailInfoList) {
                if (param.getEmail().equals(elem.getEmail())) {
                    elem.setSecCode(param.getSecCode());
                    elem.setSendDateTime(param.getSendDateTime());
                    elem.setIpAddr(param.getIpAddr());
                    isReplace = true;
                    break;
                }
            }
            if (!isReplace) {
                sendEmailInfoList.add(param);
            }
        }
    }

    /**
     * 存放3分钟内有效的验证码信息：<br/>
     * 验证是否在X分钟内同一email多次注册(还剩XX秒可以再次验证)<br/>
     */
    public int secondsBetweenSignUp(String email) {
        if (CollectionUtils.isEmpty(sendEmailInfoList)) {
            return 0;
        }
        LocalDateTime now = LocalDateTime.now();
        long verifySeconds = Long.parseLong(verifyMinutes) * 60;
        for (SendEmailInfo elem : sendEmailInfoList) {
            if (email.equals(elem.getEmail())) {
                long seconds = getRemainingSeconds(elem.getSendDateTime(), now);
                if (0L <= seconds && seconds < verifySeconds) {
                    return Integer.parseInt(String.valueOf(verifySeconds - seconds));
                }
            }
        }
        return 0;
    }

    private long getRemainingSeconds(LocalDateTime prev, LocalDateTime now) {
        return Duration.between(prev, now).getSeconds();
    }
}
