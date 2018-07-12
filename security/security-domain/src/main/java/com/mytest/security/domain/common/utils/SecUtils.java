package com.mytest.security.domain.common.utils;

import java.util.Random;

/**
 * Created by Administrator on 2017/9/27.
 */
public class SecUtils {

    // 0-9 & A-Z的字符
    private final static char[] codes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * 随机生成验证码<br/>
     * 生成length长度的由数字和大写字符组成的随机字符串<br/>
     */
    public static String getSecurityCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int index = r.nextInt(codes.length);
            sb.append(codes[index]);
        }
        return sb.toString();
    }
}
