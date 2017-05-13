package org.zhengzhipeng.client.controller;

import com.alibaba.fastjson.JSON;
import javafx.application.Platform;
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

    @Override
    public void onReceive(Message message) {
        if ("server".equals(message.getFrom())) {
            Set set = JSON.parseObject(message.getContent(), Set.class);
            list.addAll(set);
            users.setItems(list);
        }
    }

    @Override
    public int getType() {
        return USER_LIST;
    }
}
