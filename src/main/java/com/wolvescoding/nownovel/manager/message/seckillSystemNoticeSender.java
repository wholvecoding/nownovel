package com.wolvescoding.nownovel.manager.message;

import com.wolvescoding.nownovel.core.constant.MessageSenderTypeConsts;
import org.springframework.stereotype.Component;

@Component(value = MessageSenderTypeConsts.SECKILL_SYS_NOTICE_SENDER)
public class seckillSystemNoticeSender  extends AbstractSysNoticeSender {
    @Override
    public String getTitleTemplate() {
        return "秒杀即将开始";
    }
    @Override
    public String getContentTemplate() {
        return "{}秒杀，{}即将开始，不要错过哦！点击 {} 前往。";

    }


}
