package com.mytest.security.web.common.message;

/**
 * Created by Administrator on 2017/9/23.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.util.StringUtils;


public class Messages implements Serializable, Iterable<Message> {

    private static final long serialVersionUID = 1L;
    private final MessageType type;
    private final List<Message> list;
    public static final String DEFAULT_MESSAGES_ATTRIBUTE_NAME = StringUtils.uncapitalize(Messages.class.getSimpleName());

    public Messages(MessageType type) {
        this(type, (Message[])null);
    }

    public Messages(MessageType type, Message... messages) {
        this.list = new ArrayList();
        if(type == null) {
            throw new IllegalArgumentException("type must not be null!");
        } else {
            this.type = type;
            if(messages != null) {
                this.addAll(messages);
            }

        }
    }

    public MessageType getType() {
        return this.type;
    }

    public List<Message> getList() {
        return this.list;
    }

    public Messages add(Message message) {
        if(message != null) {
            this.list.add(message);
            return this;
        } else {
            throw new IllegalArgumentException("message must not be null");
        }
    }

    public Messages add(String code) {
        if(code != null) {
            this.add(Message.fromCode(code, new Object[0]));
            return this;
        } else {
            throw new IllegalArgumentException("code must not be null");
        }
    }

    public Messages add(String code, Object... args) {
        if(code != null) {
            this.add(Message.fromCode(code, args));
            return this;
        } else {
            throw new IllegalArgumentException("code must not be null");
        }
    }

    public Messages addAll(Message... messages) {
        if(messages == null) {
            throw new IllegalArgumentException("messages must not be null");
        } else {
            Message[] arr$ = messages;
            int len$ = messages.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Message message = arr$[i$];
                this.add(message);
            }

            return this;
        }
    }

    public Messages addAll(Collection<Message> messages) {
        if(messages == null) {
            throw new IllegalArgumentException("messages must not be null");
        } else {
            Iterator i$ = messages.iterator();

            while(i$.hasNext()) {
                Message message = (Message)i$.next();
                this.add(message);
            }

            return this;
        }
    }

    public boolean isNotEmpty() {
        return !this.list.isEmpty();
    }

    public static Messages success() {
        return new Messages(MessageType.SUCCESS);
    }

    public static Messages info() { return new Messages(MessageType.INFO); }

    public static Messages warning() {
        return new Messages(MessageType.WARNING);
    }

    public static Messages error() {
        return new Messages(MessageType.ERROR);
    }

    public static Messages danger() {
        return new Messages(MessageType.DANGER);
    }

    public Iterator<Message> iterator() {
        return this.list.iterator();
    }

    @Override
    public String toString() {
        return "Messages{" +
                "type=" + type +
                ", list=" + list +
                '}';
    }
}
