package com.moox.system.support.websocket;

import com.moox.system.util.JsonUtil;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * 信息发布器
 * 
 * @author tanghom
 * 
 */
public class MsgPublisher {
   
    /**
     * 消息发布器实例
     */
    private static MsgPublisher instance = new MsgPublisher();

    /**
     * 私有构造器
     */
    private MsgPublisher() {
    }

    /**
     * 获取消息发布器实例
     * 
     * @return 消息发布器实例
     */
    public static MsgPublisher getInstance() {
        return instance;
    }


    /**
     * 给所有人发送信息
     * 
     * @param receivers
     *            接收人
     * @param sender
     *            发送人
     * @param msg
     *            信息
     */
    public void publish(String title,String msg) {
    	Map<String, Object> param= new HashMap<String, Object>();
    	param.put("title", title);
    	param.put("msg", msg);
    	String data = JsonUtil.toJSONString(param);
        SystemWebSocketHandler handler = new SystemWebSocketHandler();
    	handler.sendMessageToUsers(new TextMessage(data));
    }

    /**
     * 给某人推送信息 
     * 
     * @param receiver
     *            接收人
     * @param message
     *            数据
     */
    public void publish(Long receiver, String title,String msg) {
    	Map<String, Object> param= new HashMap<String, Object>();
    	param.put("title", title);
    	param.put("msg", msg);
    	String data = JsonUtil.toJSONString(param);
    	SystemWebSocketHandler handler = new SystemWebSocketHandler();
     	handler.sendMessageToUser(receiver, new TextMessage(data));
    }

}
