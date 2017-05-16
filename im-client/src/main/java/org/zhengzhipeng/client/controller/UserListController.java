package org.zhengzhipeng.client.controller;

import com.alibaba.fastjson.JSON;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

/**
 * 用户列表 控制器
 *
 * @author zhengzhipeng
 * @since 2017/5/13
 */
public class UserListController extends BaseController implements Connection.MessageListener{

    public ListView<String> users = new ListView<>();
    ObservableList<String> list = FXCollections.observableArrayList();
    // chat pane
    private Pane pane;
    private ChatController chatController;

    private boolean isAdd = false;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public ChatController getChatController() {
        return chatController;
    }

    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }

    public void addClickEvent() {
        if (!isAdd) {
            // 绑定点击事件 弹出聊天窗口
            users.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                        final Stage[] stage = {getStage(newValue)};
                        if (stage[0] == null) {
                            Platform.runLater(() -> {
                                if (!isFirst) {
                                    FXMLLoader loader = getLoader(CHAT_PATH);
                                    try {
                                        pane = loader.load();
                                    } catch (IOException e) {
                                    }
                                    chatController = loader.getController();
                                }
                                chatController.setWho(new TextField(newValue));
                                stage[0] = new Stage();
                                stage[0].setOnCloseRequest(event -> removeStage(newValue));
                                addStage(newValue, stage[0]);
                                stage[0].setTitle(newValue);
                                stage[0].setScene(new Scene(pane));
                                stage[0].show();
                            });
                        }

                    });
            isAdd = true;
        }
    }

    @Override
    public void onReceive(Message message) {
        if ("server".equals(message.getFrom())) {
            Set set = JSON.parseObject(message.getContent(), Set.class);
            list.clear();
            set.remove(message.getTo());
            list.addAll(set);
            users.setItems(list);
            addClickEvent();
        }
    }

    @Override
    public int getType() {
        return USER_LIST;
    }
}
