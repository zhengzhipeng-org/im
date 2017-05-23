package org.zhengzhipeng.client.listener;

import com.alibaba.fastjson.JSON;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.zhengzhipeng.client.FXMLObj;
import org.zhengzhipeng.client.controller.BaseController;
import org.zhengzhipeng.client.controller.UserListController;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

import java.util.Set;

import static org.zhengzhipeng.client.controller.BaseController.*;

/**
 * 与服务器 用户列表交互
 *
 * @author zhengzhipeng
 * @since 2017/5/19
 */
public class UserListListener implements Connection.MessageListener{

    private FXMLObj<UserListController> fxmlObj;
    private boolean isLaunch = false;

    @Override
    public void onReceive(Message message) {
        if ("server".equals(message.getFrom())) {
            Set set = JSON.parseObject(message.getContent(), Set.class);
            set.remove(message.getTo());
            if (fxmlObj == null) {
                fxmlObj = getPane(APP_UI_PATH);
            }
            Stage stage = BaseController.primaryStage;
            UserListController controller = fxmlObj.getController();
            ObservableList<String> list = controller.getList();
            if (list.size() > 0) {
                list.clear();
            }
            list.addAll(set);
            ListView<String> users = controller.getUsers();
            users.setItems(list);

            if (!isLaunch) {
                isLaunch = true;
                controller.addEventClick();
                Platform.runLater(() -> {
                    stage.setTitle(message.getTo());
                    stage.setScene(new Scene(fxmlObj.getPane()));
                });
            }
        }
    }

    @Override
    public int getType() {
        return USER_LIST;
    }
}
