package org.zhengzhipeng.server;

import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

import java.io.IOException;

/**
 * 消息转发类
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class ChatListener implements Connection.MessageListener {

    private ConnectionManager manager;

    public ChatListener(ConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void onReceive(Message msg) {
        Connection connection = manager.getConnection(msg.getTo());
        if (connection == null) {
            System.out.println(msg.getTo() + "用户不在线");
            return;
        }
        try {
            connection.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发送消息失败.");
        }
    }

    @Override
    public int getType() {
        return CHAT;
    }

}
