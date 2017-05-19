package org.zhengzhipeng.client.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.zhengzhipeng.client.Chat;
import org.zhengzhipeng.client.FXMLObj;
import org.zhengzhipeng.common.Connection;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * base controller
 *
 * @author zhengzhipeng
 * @since 2017/5/12
 */
public class BaseController {

    public static final String LOGIN_PATH = "views/login.fxml";
    public static final String APP_UI_PATH = "views/app-ui.fxml";
    public static final String CHAT_PATH = "views/chat.fxml";

    public static final Map<String, Chat> chats = new HashMap<>();

    public static Connection connection;
    public static String currentUser;
    public static Stage primaryStage;

    public static void addChat(String key, Chat chat) {
        chats.put(key, chat);
    }

    public static Chat getChat(String key) {
        return chats.get(key);
    }

    public static void removeChat(String key) {
        chats.remove(key);
    }

    /**
     * 加载fxml
     * @param path 文件路径
     * @return FXMLObj
     */
    public static FXMLObj getPane(String path) {
        URL res = BaseController.class.getClassLoader().getResource(path);
        if (res == null) {
            throw new IllegalStateException(path + "文件找不到。");
        }
        FXMLLoader loader = new FXMLLoader(res);
        Pane pane = null;
        try {
             pane = loader.load();
        } catch (IOException e) {
            System.out.println("加载文件失败. path: " + path);
        }
        FXMLObj fxmlObj = new FXMLObj();
        fxmlObj.setController(loader.getController());
        fxmlObj.setPane(pane);
        return fxmlObj;
    }
}
