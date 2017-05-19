package org.zhengzhipeng.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.zhengzhipeng.client.controller.BaseController;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * 登陆界面
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class LoginUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getClassLoader().getResource(BaseController.LOGIN_PATH);
        if (resource == null) {
            throw new FileNotFoundException(BaseController.LOGIN_PATH);
        }
        FXMLLoader loader = new FXMLLoader(resource);
        Pane root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        BaseController.primaryStage = primaryStage;
    }
}
