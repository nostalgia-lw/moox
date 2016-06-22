package com.moox.system.listenner;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * wrj
 * session监听
 * Created by Administrator on 2016-06-21.
 */
public class SessionListenner  implements HttpSessionAttributeListener{
    // 已经登录用户session
    public static Map<String, HttpSession> loginedUser = new HashMap<String, HttpSession>();
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String attrName = event.getName();
        // 监听到为属性userName的添加
        if ("userName".equals(attrName)) {
            String value = (String) event.getValue();
            HttpSession session = loginedUser.get(value);
            if (session != null) {
                session.removeAttribute(attrName);
            }
            loginedUser.put(value, event.getSession());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
