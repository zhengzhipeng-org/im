package org.zhengzhipeng.client;

import org.zhengzhipeng.common.Connection;
import org.zhengzhipeng.common.Message;

/**
 * 聊天 监听器
 *
 * @author zhengzhipeng
 * @since 2017/5/11
 */
public class ChartListener implements Connection.MessageListener{

    @Override
    public void onReceive(Message message) {

    }

    @Override
    public int getType() {
        return CHART;
    }
}
