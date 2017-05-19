package org.zhengzhipeng.client.listener;

import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

import static org.zhengzhipeng.client.controller.BaseController.currentUser;

/**
 * 与服务器 登录交互
 * @author zhengzhipeng
 * @since 2017/5/19
 */
public class LoginListener implements Connection.MessageListener{

    @Override
    public void onReceive(Message message) {
        // 登陆响应
        if ("server".equals(message.getFrom())) {
            if ("ok".equals(message.getContent())) {
                System.out.println("登陆成功");
                currentUser = message.getTo();
            }
        }
    }

    @Override
    public int getType() {
        return LOGIN;
    }
}
