package org.zhengzhipeng.common;

/**
 * 消息类
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class Message {

    /** 消息类型 */
    private int type;
    /** 目标用户的唯一标识 */
    private String to;
    /** 来源用户的唯一标识 */
    private String from;
    /** 消息内容 */
    private String content;

    public Message() {
    }

    public Message(int type, String from, String to, String content) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
