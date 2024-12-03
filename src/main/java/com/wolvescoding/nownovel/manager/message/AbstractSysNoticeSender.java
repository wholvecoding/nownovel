package com.wolvescoding.nownovel.manager.message;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
public abstract class AbstractSysNoticeSender extends AbstractMessageSender {

    @Override
    protected void sendMessage(Long toUserId, String title, String content) {

        LocalDateTime messageTime = LocalDateTime.now();
        log.info("系统通知发送成功，{},{},{},{}", toUserId, title, content, messageTime);
    }
}
