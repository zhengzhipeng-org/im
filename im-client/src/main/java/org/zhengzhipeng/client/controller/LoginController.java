package org.zhengzhipeng.client.controller;

import com.alibaba.fastjson.JSON;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.zhengzhipeng.client.listener.ChatListener;
import org.zhengzhipeng.client.listener.LoginListener;
import org.zhengzhipeng.client.listener.UserListListener;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Login;
import org.zhengzhipeng.common.Message;

import java.io.IOException;

/**
 * 登陆 fx控制器
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class LoginController extends BaseController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void login() throws IOException {
        // 与服务器建立连接
        connection = new Connection("127.0.0.1", 10086);
        connection.addMessageListener(new LoginListener());
        connection.addMessageListener(new UserListListener());
        connection.addMessageListener(new ChatListener());
        new Thread(connection).start();
        // 发送登陆请求
        connection.sendMessage(new Message(1, username.getText(), "server",
                JSON.toJSONString(new Login(username.getText(), password.getText()))));
    }

}
