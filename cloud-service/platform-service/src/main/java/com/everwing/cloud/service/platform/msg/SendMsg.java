package com.everwing.cloud.service.platform.msg;

/**
 * @author DELL
 * @title: SendMsg
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/2/259:08
 */
public interface SendMsg {
    /**
     * 发送消息
     * @param msg 消息内容json
     */
    public void sendMsg(String msg);
}
