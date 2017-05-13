package org.zhengzhipeng.client.controller;

import javafx.stage.Stage;

/**
 * base controller
 *
 * @author zhengzhipeng
 * @since 2017/5/12
 */
public class BaseController {

    protected Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
