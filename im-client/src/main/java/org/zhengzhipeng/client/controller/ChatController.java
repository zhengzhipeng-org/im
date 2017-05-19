package org.zhengzhipeng.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

import java.io.IOException;

/**
 * 聊天 监听器
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class ChatController extends BaseController {

    @FXML
    private TextArea showContent;
    @FXML
    private TextArea sendContent;
    @FXML
    private TextField who;

    public void setWho(TextField who) {
        this.who = who;
    }

    public void showMessage(String username, String content) {
        showContent.appendText(username + " : " + content + "\r\n");
    }

    public void sendMessage() throws IOException {
        String text = sendContent.getText();
        showContent.appendText(currentUser + " : " + text + "\r\n");
        Message msg = new Message();
        msg.setFrom(currentUser);
        msg.setTo(who.getText());
        msg.setType(Connection.MessageListener.CHAT);
        msg.setContent(text);
        connection.sendMessage(msg);
        sendContent.setText("");
    }
}
