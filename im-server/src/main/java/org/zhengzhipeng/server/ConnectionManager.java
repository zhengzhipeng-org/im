package org.zhengzhipeng.server;

import org.zhengzhipeng.common.Connection;

import java.util.*;

/**
 * 连接管理
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class ConnectionManager {

    private Map<String, Connection> connections = new HashMap<>();

    public void addConnection(String id, Connection connection) {
       connections.put(id, connection);
    }

    public Connection getConnection(String id) {
        return connections.get(id);
    }

    public void removeConnection(String id) {
        connections.remove(id);
    }

    public Set<String> getAllConnectionKey() {
        return connections.keySet();
    }

    public Map<String, Connection> getConnections() {
        return connections;
    }
}
