package com.moox.system.shiro.filter;

import com.moox.system.dao.UserDao;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>User: tanghom
 * <p>Date: 15-11-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserDao userDao;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        String loginName = (String)SecurityUtils.getSubject().getPrincipal();
//        request.setAttribute("user", userDao.getUserByloginName(loginName));
        return true;
    }
}
