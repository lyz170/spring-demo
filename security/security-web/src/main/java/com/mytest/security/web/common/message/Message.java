package com.mytest.security.web.common.message;

/**
 * Created by Administrator on 2017/9/23.
 */
import java.io.Serializable;
import java.util.Arrays;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final String code;
    private final Object[] args;
    private final String text;

    public Message(String code, Object[] args, String text) {
        this.code = code;
        this.args = args == null ? EMPTY_ARRAY : args;
        this.text = text;
    }

    public static Message fromCode(String code, Object... args) {
        return new Message(code, args, (String) null);
    }

    public static Message fromText(String text) {
        return new Message((String) null, EMPTY_ARRAY, text);
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + Arrays.hashCode(this.args);
        result1 = 31 * result1 + (this.code == null ? 0 : this.code.hashCode());
        result1 = 31 * result1 + (this.text == null ? 0 : this.text.hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Message other = (Message) obj;
            if (this.code == null) {
                if (other.code != null) {
                    return false;
                }
            } else if (!this.code.equals(other.code)) {
                return false;
            }

            if (!Arrays.equals(this.args, other.args)) {
                return false;
            } else {
                if (this.text == null) {
                    if (other.text != null) {
                        return false;
                    }
                } else if (!this.text.equals(other.text)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "code='" + code + '\'' +
                ", args=" + Arrays.toString(args) +
                ", text='" + text + '\'' +
                '}';
    }
}

