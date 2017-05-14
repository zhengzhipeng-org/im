package org.zhengzhipeng.common;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class Connection implements Runnable{

    private Socket socket;
    /** 接收流 */
    private DataInputStream is;
    /** 发送流 */
    private DataOutputStream os;
    /** 消息监听器 */
    private List<MessageListener> listeners = new ArrayList<>();
    /** 关闭标识 */
    private boolean close = false;

    public Connection(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * 关闭连接
     */
    public void close() {
        close = true;
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
        IOUtils.closeQuietly(socket);
        listeners.clear();
    }

    /**
     * 发送消息
     * @param msg 消息
     * @throws IOException io异常
     */
    public void sendMessage(Message msg) throws IOException {
        os.writeUTF(JSON.toJSONString(msg));
    }

    @Override
    public void run() {
        while (!close) {
            Message msg = null;
            try {
                msg = JSON.parseObject(is.readUTF(), Message.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (MessageListener listener : listeners) {
                if (listener.getType() == msg.getType()) {
                    listener.onReceive(msg);
                    break;
                }
            }
        }
    }

    public void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        listeners.remove(listener);
    }

    public interface MessageListener {
        // 登陆
        int LOGIN = 1;
        // 聊天
        int CHAT = 2;
        // 用户列表
        int USER_LIST = 3;

        void onReceive(Message message);

        int getType();
    }
}
