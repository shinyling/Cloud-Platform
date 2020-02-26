package com.everwing.cloud.service.admin.msg;

import com.everwing.cloud.common.stream.channel.ChannelName;
import com.everwing.cloud.common.stream.core.MsgInputChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author DELL shiny
 * @create 2020/2/25
 */
@Slf4j
@EnableBinding(MsgInputChannel.class)
public class CompanyMsgListener {

    @StreamListener(target = ChannelName.COMPANY_RECEIVE)
    public void listenCompany(Message<String> message){
        log.info("收到消息[{}]",message.getPayload());
    }
}
