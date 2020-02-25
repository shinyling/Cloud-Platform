package com.everwing.cloud.common.stream.core;

import com.everwing.cloud.common.stream.channel.ChannelName;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author DELL shiny
 * @create 2020/2/25
 */
public interface MsgInputChannel {

    @Input(ChannelName.COMPANY)
    SubscribableChannel addCompany();
}
