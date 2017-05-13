package org.zhengzhipeng.common;

/**
 * 响应
 *
 * @author zhengzhipeng
 * @since 2017/5/13
 */
public class Response {

    private String status;
    private String content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
