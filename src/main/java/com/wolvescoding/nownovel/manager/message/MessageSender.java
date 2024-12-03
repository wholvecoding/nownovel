package com.wolvescoding.nownovel.manager.message;

public interface MessageSender {
    /**
     * 发送消息，支持动态消息标题和动态消息内容
     *
     * @param toUserId 消息接收方的用户ID
     * @param args     用来动态生成消息标题和消息内容的参数列表
     */
    void sendMessage(Long toUserId, Object... args);
}
