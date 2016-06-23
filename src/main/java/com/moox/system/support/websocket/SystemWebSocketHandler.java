package com.moox.system.support.websocket;

import com.moox.system.util.CommonKey;
import org.apache.log4j.Logger;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;

public class SystemWebSocketHandler implements WebSocketHandler {
	 
    /**
     * 日志实例
     */
    private static Logger logger = Logger.getLogger(SystemWebSocketHandler.class);
 
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
 
 
//    @Autowired
//    private WebSocketService webSocketService;
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info(session.getId() + " connect to the websocket success......");
        users.add(session);
//        String userName = (String) session.getAttributes().get(CommonKey.WEBSOCKET_USER_SESSION);
//        if(userName!= null){
            //查询未读消息
//            int count = webSocketService.getUnReadNews((String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
 
//            session.sendMessage(new TextMessage(count + ""));
//        	int count = 5;
//           session.sendMessage(new TextMessage(count + ""));
//        }
    }
 
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    }
 
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.info(session.getId() + " websocket connection closed......");
        users.remove(session);
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info(session.getId() + " websocket connection closed......");
        users.remove(session);
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
 
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 给某个用户发送消息
     *
     * @param receiver 接收人名称
     * @param message
     */
    public void sendMessageToUser(Long receiver, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(CommonKey.WEBSOCKET_USER_SESSION).equals(receiver)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}