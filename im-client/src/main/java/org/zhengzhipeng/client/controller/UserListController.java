package org.zhengzhipeng.client.controller;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.zhengzhipeng.client.Chat;
import org.zhengzhipeng.client.FXMLObj;

/**
 * 用户列表 控制器
 *
 * @author zhengzhipeng
 * @since 2017/5/13
 */
public class UserListController extends BaseController{

    @FXML
    private ListView<String> users = new ListView<>();
    @FXML
    private ObservableList<String> list = FXCollections.observableArrayList();

    public ObservableList<String> getList() {
        return list;
    }

    public ListView<String> getUsers() {
        return users;
    }

    public void addEventClick() {
        // 绑定点击事件 弹出聊天窗口
        users.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    final Chat[] chat = {getChat(newValue)};
                    if (chat[0] == null) {
                        Platform.runLater(() -> {
                            FXMLObj<ChatController> fxmlObj = getPane(CHAT_PATH);
                            ChatController controller = fxmlObj.getController();
                            controller.setWho(new TextField(newValue));
                            Pane pane = fxmlObj.getPane();
                            Stage stage = new Stage();
                            stage.setOnCloseRequest(event -> removeChat(newValue));
                            stage.setTitle(newValue);
                            stage.setScene(new Scene(pane));
                            stage.show();
                            chat[0] = new Chat();
                            chat[0].setStage(stage);
                            chat[0].setController(controller);
                            addChat(newValue, chat[0]);
                        });
                    }
                });
    }
}
