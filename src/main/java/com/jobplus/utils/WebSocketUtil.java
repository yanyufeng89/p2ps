package com.jobplus.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by eric on 2016/9/9.
 */
public class WebSocketUtil {

    public static String websocketIp = "192.168.0.39";
    public static int websocketPort = 7397;

    /**
     * 发送消息
     *
     * @param receiver 接受者ID
     * @param content  消息内容
     * @return
     */
    public static void send(String receiver, String content) {
        if (StringUtils.isNotBlank(receiver) && StringUtils.isNotBlank(content))
            HttpClientUtils.doGet("http://" + websocketIp + ":" + websocketPort + "/websocket?receiver=" + receiver + "&&content=" + content);
    }
}
