package com.mytest.security.web.common.message;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

/**
 * Created by Administrator on 2017/9/23.
 */
public final class MessageUtils {

    private MessageUtils() {}

    public static String resolveMessage(Message message, MessageSource messageSource, Locale locale) throws NoSuchMessageException {

        String msg = null;
        String code = message.getCode();
        if(code != null) {
            try {
                msg = messageSource.getMessage(code, message.getArgs(), locale);
            } catch (NoSuchMessageException e) {
                String text = message.getText();
                if(text == null) {
                    throw e;
                }

                msg = text;
            }
        } else {
            msg = message.getText();
        }

        return msg;
    }

    public static String resolveMessage(Message message, MessageSource messageSource) {
        return resolveMessage(message, messageSource, Locale.getDefault());
    }
}
