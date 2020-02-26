package com.everwing.cloud.common.stream.core;

import com.everwing.cloud.common.stream.channel.ChannelName;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author DELL
 * @title: SendOutputChannel
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/2/259:11
 */
public interface SendOutputChannel {

    @Output(ChannelName.COMPANY_SEND)
    MessageChannel companySender();

}
