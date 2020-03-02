package com.everwing.cloud.service.admin.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import com.alibaba.fastjson.JSON;
import com.everwing.cloud.common.stream.channel.ChannelName;
import com.everwing.cloud.common.stream.core.MsgInputChannel;
import com.everwing.cloud.service.admin.service.InitSchemeService;
import com.everwing.cloud.service.platform.vo.CompanyVo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DELL shiny
 * @create 2020/2/25
 */
@Slf4j
@EnableBinding(MsgInputChannel.class)
public class CompanyMsgListener {

    @Autowired
    private InitSchemeService initSchemeService;

    @StreamListener(target = ChannelName.COMPANY_RECEIVE)
    public void listenCompany(Message<String> message){
        try {
            //todo 校验接收的数据是否重复
            String msg=message.getPayload();
            CompanyVo companyVo= JSON.parseObject(msg,CompanyVo.class);
            log.info("收到消息[{}]",companyVo);
            initSchemeService.init(companyVo);
        } catch (Exception e) {
            //todo 消费消息异常处理
        }
    }
}
