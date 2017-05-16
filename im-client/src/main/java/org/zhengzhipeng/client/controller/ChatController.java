package org.zhengzhipeng.client.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    public TextField who;
    // chat pane
    private Pane pane;

    public Pane getPane() {
        return pane;
    }

    public TextField getWho() {
        return who;
    }

    public void setWho(TextField who) {
        this.who = who;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void onReceive(Message msg) {
        String from = msg.getFrom();
        final Stage[] stage = {getStage(from)};
        if (stage[0] == null) {
            Platform.runLater(() -> {
                if (!isFirst) {
                    FXMLLoader loader = getLoader(CHAT_PATH);
                    try {
                        pane = loader.load();
                        ChatController chatController = loader.getController();
                        chatController.setWho(new TextField(from));
                    } catch (IOException e) {
                        System.out.println();
                    }
                } else {
                    this.setWho(new TextField(from));
                }
                stage[0] = new Stage();
                stage[0].setOnCloseRequest(event -> removeStage(from));
                addStage(from, stage[0]);
                stage[0].setTitle(from);
                stage[0].setScene(new Scene(pane));
                stage[0].show();
            });
        }
        showContent.appendText(msg.getFrom() + " : " + msg.getContent() + "\r\n");
    }

    @Override
    public int getType() {
        return CHAT;
    }

    public void sendMessage() throws IOException {
        String text = sendContent.getText();
        showContent.appendText(currentUser + " : " + text + "\r\n");
        Message msg = new Message();
        msg.setFrom(currentUser);
        msg.setTo(who.getText());
        msg.setType(CHAT);
        msg.setContent(text);
        connection.sendMessage(msg);
        sendContent.setText("");
    }
}
