package org.zhengzhipeng.client.controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.zhengzhipeng.common.Connection;

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

    public static final String PRIMARY_STAGE = "primaryStage";
    public static final Map<String, Stage> stages = new HashMap<>();

    public static Connection connection;
    public static String currentUser;
    // 是否是第一次用 chat pane
    public static boolean isFirst = true;

    public static void addPrimaryStage(Stage stage) {
        addStage(PRIMARY_STAGE, stage);
    }

    public static Stage getPrimaryStage() {
        return getStage(PRIMARY_STAGE);
    }

    public static void addStage(String key, Stage stage) {
        stages.put(key, stage);
    }

    public static Stage getStage(String key) {
        return stages.get(key);
    }

    public static void removeStage(String key) {
        stages.remove(key);
        isFirst = false;
    }

    /**
     * 获取loader
     * @param path 文件路径
     * @return FXMLLoader
     */
    public FXMLLoader getLoader(String path) {
        URL res = getClass().getClassLoader().getResource(path);
        if (res == null) {
            throw new IllegalStateException(path + "文件找不到。");
        }
        return new FXMLLoader(res);
    }
}
