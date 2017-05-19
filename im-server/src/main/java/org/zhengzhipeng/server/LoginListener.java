package org.zhengzhipeng.server;

import com.alibaba.fastjson.JSON;
import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Login;
import org.zhengzhipeng.common.Message;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 服务端接收登陆
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class LoginListener implements Connection.MessageListener {

    private ConnectionManager manager;
    private Connection connection;
    private ChatListener dispatcher;

    public LoginListener(ConnectionManager manager, Connection connection, ChatListener dispatcher) {
        this.manager = manager;
        this.connection = connection;
        this.dispatcher = dispatcher;
    }

    @Override
    public void onReceive(Message message) {
        Message response = new Message();
        response.setFrom("server");
        response.setTo(message.getFrom());
        response.setType(LOGIN);
        if (message.getTo().equals("server")) {
            Login login = JSON.parseObject(message.getContent(), Login.class);
            if ("admin".equals(login.getUsername()) || "zzp".equals(login.getUsername()) ||
                    "cy".equals(login.getUsername()) || "ywj".equals(login.getUsername())) {
                try {
                    response.setContent("ok");
                    connection.sendMessage(response);
                } catch (IOException e) {
                    System.out.println("发送登陆响应消息失败");
                    e.printStackTrace();
                }
                manager.addConnection(message.getFrom(), connection);
                connection.addMessageListener(dispatcher);
                connection.removeMessageListener(this);

                // 推送当前在线用户到每个客户端
                Map<String, Connection> connections = manager.getConnections();
                Iterator<Map.Entry<String, Connection>> iterator = connections.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Connection> next = iterator.next();
                    String client = next.getKey();
                    Connection connection = next.getValue();

                    Message userDataResp = new Message();
                    userDataResp.setType(USER_LIST);
                    userDataResp.setFrom("server");
                    userDataResp.setTo(client);
                    // 用户列表
                    Set<String> users = manager.getAllConnectionKey();
                    userDataResp.setContent(JSON.toJSONString(users));
                    try {
                        connection.sendMessage(userDataResp);
                    } catch (IOException e) {
                        System.out.println("发送用户列表响应消息失败");
                        e.printStackTrace();
                    }
                }
                return;
            }
            try {
                response.setContent("fail");
                connection.sendMessage(response);
            } catch (IOException e) {
                System.out.println("发送登陆响应消息失败");
                e.printStackTrace();
            }
        }
        connection.close();
    }

    @Override
    public int getType() {
        return LOGIN;
    }
}
