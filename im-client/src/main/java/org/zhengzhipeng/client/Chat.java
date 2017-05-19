package org.zhengzhipeng.client;

import javafx.stage.Stage;
import org.zhengzhipeng.client.controller.ChatController;

/**
 * 聊天对象  封装stage和controller
 *
 * @author zhengzhipeng
 * @since 2017/5/20
 */
public class Chat {

    private ChatController controller;
    private Stage stage;

    public ChatController getController() {
        return controller;
    }

    public void setController(ChatController controller) {
        this.controller = controller;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
