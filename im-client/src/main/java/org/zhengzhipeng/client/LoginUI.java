package org.zhengzhipeng.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.zhengzhipeng.client.controller.LoginController;

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
        URL resource = getClass().getClassLoader().getResource("views/login.fxml");
        if (resource == null) {
            throw new FileNotFoundException("views/login.fxml");
        }
        FXMLLoader loader = new FXMLLoader(resource);
        Pane root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LoginController controller = loader.getController();
        controller.setStage(primaryStage);
    }
}
