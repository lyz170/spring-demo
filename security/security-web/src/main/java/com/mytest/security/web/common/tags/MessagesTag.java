package com.mytest.security.web.common.tags;

/**
 * Created by Administrator on 2017/9/23.
 */

import java.lang.reflect.Array;
import java.util.Locale;

import javax.servlet.jsp.JspException;

import com.mytest.security.web.common.message.MessageType;
import com.mytest.security.web.common.message.MessageUtils;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.springframework.web.servlet.tags.form.TagWriter;
import com.mytest.security.web.common.message.Message;
import com.mytest.security.web.common.message.Messages;

public class MessagesTag extends RequestContextAwareTag {

    private static final long serialVersionUID = 1L;
    private String messagesAttributeName = Messages.DEFAULT_MESSAGES_ATTRIBUTE_NAME;
    protected static final String DEFAULT_LIST_ELEMENT = "div";
    protected static final String DEFAULT_LIST_CLASS = null;
    protected static final String DEFAULT_LINE_ELEMENT = "p";
    protected static final String DEFAULT_LINE_CLASS = null;

    private String listElem = DEFAULT_LIST_ELEMENT;
    private String listClass = DEFAULT_LIST_CLASS;
    private String lineElem = DEFAULT_LINE_ELEMENT;
    private String lineClass = DEFAULT_LINE_CLASS;
    private String messagesType = null;


    TagWriter createTagWriter() {
        TagWriter tagWriter = new TagWriter(this.pageContext);
        return tagWriter;
    }

    @Override
    protected int doStartTagInternal() throws JspException {

        TagWriter tagWriter = createTagWriter();

        Object messages = this.pageContext.findAttribute(messagesAttributeName);

        if (messages != null) {

            if (StringUtils.hasText(listElem)) {
                tagWriter.startTag(listElem);
                if (StringUtils.hasText(listClass)) {
                    tagWriter.writeAttribute("class", listClass);
                }
            }
            writeMessages(tagWriter, messages);
            if (StringUtils.hasText(listElem)) {
                tagWriter.endTag();
            }
        }

        return EVAL_BODY_INCLUDE;
    }

    protected void writeMessages(TagWriter tagWriter, Object messages)
            throws JspException {

        String msgType = getType(messages);

        Class<?> clazz = messages.getClass();
        if (Iterable.class.isAssignableFrom(clazz)) {
            Iterable<?> col = (Iterable<?>) messages;
            for (Object message : col) {
                writeMessage(tagWriter, message, msgType);
            }
        } else if (clazz.isArray()) {
            Class<?> type = clazz.getComponentType();
            if (Object.class.isAssignableFrom(type)) {
                Object[] arr = (Object[]) messages;
                for (Object message : arr) {
                    writeMessage(tagWriter, message, msgType);
                }
            } else {
                int len = Array.getLength(messages);
                for (int i = 0; i < len; i++) {
                    Object message = Array.get(messages, i);
                    writeMessage(tagWriter, message, msgType);
                }
            }
        } else {
            writeMessage(tagWriter, messages, msgType);
        }
    }

    /**
     * <p class="XXX">xxx</p>
     */
    protected void writeMessage(TagWriter tagWriter, Object message, String type)
            throws JspException {
        if (message != null) {
            if (StringUtils.hasText(lineElem)) {
                tagWriter.startTag(lineElem);
                if (StringUtils.hasText(lineClass)) {
                    tagWriter.writeAttribute("class", lineClass);
                } else {
                    String className = "";
                    if (MessageType.INFO.getType().equals(type)) {
                        className = "text-success bg-success";
                    } else if (MessageType.INFO.getType().equals(type)) {
                        className = "text-info bg-info";
                    } else if (MessageType.WARNING.getType().equals(type)) {
                        className = "text-warning bg-warning";
                    } else if (MessageType.ERROR.getType().equals(type) || MessageType.DANGER.getType().equals(type)) {
                        className = "text-danger bg-danger";
                    }
                    if (StringUtils.hasText(className)) {
                        tagWriter.writeAttribute("class", className);
                    }
                }
            }
            tagWriter.appendValue(getText(message));

            if (StringUtils.hasText(lineElem)) {
                tagWriter.endTag();
            }
        }
    }

    private String getText(Object message) {
        String text = null;

        if (message instanceof String) {
            text = (String) message;
        } else if (message instanceof Message) {
            Message msg = (Message) message;
            text = getText(msg);
        } else if (message instanceof Throwable) {
            Throwable throwable = (Throwable) message;
            text = throwable.getMessage();
        } else {
            text = getTextInOtherCase(message);
        }
        return text;
    }

    private String getText(Message message) {
        Locale locale = getRequestContext().getLocale();
        MessageSource messageSource = getRequestContext().getMessageSource();
        String text = MessageUtils.resolveMessage(message,
                messageSource, locale);
        return text;
    }

    protected String getTextInOtherCase(Object message) {
        return message.toString();
    }


    private String getType(Object messages) {
        String type = "";
        if (messagesType != null) {
            type = messagesType;
        } else if (messages instanceof Messages) {
            Messages msgs = (Messages) messages;
            type = msgs.getType().getType();
        }
        return type;
    }

    public void setMessagesAttributeName(String messagesAttributeName) {
        this.messagesAttributeName = messagesAttributeName;
    }

    public void setListElem(String listElem) {
        this.listElem = listElem;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    public void setLineElem(String lineElem) {
        this.lineElem = lineElem;
    }

    public void setLineClass(String lineClass) {
        this.lineClass = lineClass;
    }

    public void setMessagesType(String messagesType) {
        this.messagesType = messagesType;
    }
}

