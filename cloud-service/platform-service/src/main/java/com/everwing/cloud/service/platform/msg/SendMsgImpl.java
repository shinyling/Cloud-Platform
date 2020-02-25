package com.everwing.cloud.service.platform.msg;

import com.everwing.cloud.common.stream.core.SendOutputChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author DELL shiny
 * @create 2020/2/25
 */
@Slf4j
@EnableBinding(SendOutputChannel.class)
public class SendMsgImpl implements SendMsg {

    @Autowired
    private SendOutputChannel sendOutputChannel;

    @Override
    public void sendMsg(String msg) {
        boolean flag=sendOutputChannel.companySender().send(MessageBuilder.withPayload(msg).build());
        if(!flag){
            log.error("公司信息投递失败![{}]",msg);
        }
        log.info("公司信息投递成功![{}]",msg);
    }
}
