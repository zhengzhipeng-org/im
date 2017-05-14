package org.zhengzhipeng.client.controller;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

import java.io.IOException;

/**
 * 聊天 监听器
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class ChatController extends BaseController implements Connection.MessageListener {

    public TextArea showContent;
    public TextArea sendContent;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void onReceive(Message msg) {
        showContent.setText(msg.getFrom() + " : " + msg.getContent() + "\r\n");
    }

    @Override
    public int getType() {
        return CHAT;
    }

    public void sendMessage() throws IOException {
        String text = sendContent.getText();
        showContent.setText(text);
        Connection connection = getConnection();
        Message msg = new Message();
        msg.setFrom("zzp");
        msg.setTo("admin");
        msg.setType(CHAT);
        msg.setContent(text);
        connection.sendMessage(msg);
    }
}
