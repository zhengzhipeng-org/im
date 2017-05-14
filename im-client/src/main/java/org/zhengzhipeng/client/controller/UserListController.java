package org.zhengzhipeng.client.controller;

import com.alibaba.fastjson.JSON;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    private boolean isAdd = false;
    private Pane pane;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void addClickEvent() {
        if (!isAdd) {
            // 绑定点击事件 弹出聊天窗口
            users.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                        Stage stage = new Stage();
                        Platform.runLater(() -> {
                            stage.setScene(new Scene(getPane()));
                            stage.show();
                        });
                    });
            isAdd = true;
        }
    }

    @Override
    public void onReceive(Message message) {
        if ("server".equals(message.getFrom())) {
            Set set = JSON.parseObject(message.getContent(), Set.class);
            list.clear();
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
