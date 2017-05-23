package org.zhengzhipeng.client.listener;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.zhengzhipeng.client.Chat;
import org.zhengzhipeng.client.FXMLObj;
import org.zhengzhipeng.client.controller.BaseController;
import org.zhengzhipeng.client.controller.ChatController;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

/**
 * 与服务器 聊天交互
 *
 * @author zhengzhipeng
 * @since 2017/5/19
 */
public class ChatListener implements Connection.MessageListener {

    @Override
    public void onReceive(Message msg) {
        String from = msg.getFrom();
        final Chat[] chat = {BaseController.getChat(from)};
        if (chat[0] == null) {
            Platform.runLater(() -> {
                FXMLObj<ChatController> fxmlObj = BaseController.getPane(BaseController.CHAT_PATH);
                ChatController controller = fxmlObj.getController();
                controller.setWho(new TextField(from));
                Pane pane = fxmlObj.getPane();
                Stage stage = new Stage();
                stage.setOnCloseRequest(event -> BaseController.removeChat(from));
                stage.setTitle(from);
                stage.setScene(new Scene(pane));
                stage.show();
                chat[0] = new Chat();
                chat[0].setStage(stage);
                chat[0].setController(controller);
                BaseController.addChat(from, chat[0]);
                controller.showMessage(from, msg.getContent());
            });
        } else {
            Platform.runLater(() -> {
                Chat ch = chat[0];
                ChatController controller = ch.getController();
                controller.showMessage(from, msg.getContent());
            });
        }
    }

    @Override
    public int getType() {
        return CHAT;
    }
}
