package org.zhengzhipeng.server;

import org.zhengzhipeng.common.Connection;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 服务端
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10086);
        ConnectionManager manager = new ConnectionManager();
        ChatListener dispatcher = new ChatListener(manager);
        while (true) {
            Connection connection = new Connection(server.accept());
            connection.addMessageListener(new LoginListener(manager, connection, dispatcher));
            new Thread(connection).start();
        }
    }
}
