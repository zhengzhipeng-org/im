package org.zhengzhipeng.client;

import javafx.scene.layout.Pane;
import org.zhengzhipeng.client.controller.BaseController;

/**
 * 加载fxml后 封装controller和pane的对象
 *
 * @author zhengzhipeng
 * @since 2017/5/19
 */
public class FXMLObj<T extends BaseController> {

    private Pane pane;
    private T controller;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public T getController() {
        return controller;
    }

    public void setController(T controller) {
        this.controller = controller;
    }
}
