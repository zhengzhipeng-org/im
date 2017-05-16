package org.zhengzhipeng.client.controller;

import com.alibaba.fastjson.JSON;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Login;
import org.zhengzhipeng.common.Message;

import java.io.IOException;
import java.net.URL;

/**
 * 登陆 fx控制器
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class LoginController extends BaseController implements Connection.MessageListener {

    public TextField username;
    public PasswordField password;
    // 用户列表 pane
    public Pane pane;

    public void login() throws IOException {
        // 与服务器建立连接
        connection = new Connection("127.0.0.1", 10086);
        connection.addMessageListener(this);
        if (pane == null) {
            // chat
            FXMLLoader load = getLoader(CHAT_PATH);
            Pane chatPane = null;
            try {
                chatPane = load.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatController chatController = load.getController();
            chatController.setPane(chatPane);
            connection.addMessageListener(chatController);

            // user list
            FXMLLoader loader = getLoader(APP_UI_PATH);
            pane = loader.load();
            UserListController controller = loader.getController();
            controller.setChatController(chatController);
            controller.setPane(chatPane);
            connection.addMessageListener(controller);
        }
        new Thread(connection).start();
        // 发送登陆请求
        connection.sendMessage(new Message(1, username.getText(), "server",
                JSON.toJSONString(new Login(username.getText(), password.getText()))));
    }

    @Override
    public void onReceive(Message message) {
        // 登陆响应
        if ("server".equals(message.getFrom())) {
            if ("ok".equals(message.getContent())) {
                System.out.println("登陆成功");
                currentUser = message.getTo();
                Stage stage = getPrimaryStage();
                Platform.runLater(() -> {
                    stage.setTitle(currentUser);
                    stage.setScene(new Scene(pane));
                });
            }
        }
    }

    @Override
    public int getType() {
        return LOGIN;
    }
}
