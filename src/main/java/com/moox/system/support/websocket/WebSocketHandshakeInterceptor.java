package com.moox.system.support.websocket;

import com.moox.system.entity.User;
import com.moox.system.util.CommonKey;
import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 这个的主要作用是取得当前请求中的用户名，并且保存到当前的WebSocketHandler中，以便确定WebSocketHandler所对应的用户，具体可参考HttpSessionHandshakeInterceptor
 * @author tanghom
 *
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
	 
	 /**
     * 日志实例
     */
    private static Logger logger = Logger.getLogger(WebSocketHandshakeInterceptor.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object
                > attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userId区分WebSocketHandler，以便定向发送消息
                User user = (User) session.getAttribute(CommonKey.USER_SESSION);
                attributes.put(CommonKey.WEBSOCKET_USER_SESSION,user.getId());
            }
        }
        return true;
    }
 
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
 
    }
}