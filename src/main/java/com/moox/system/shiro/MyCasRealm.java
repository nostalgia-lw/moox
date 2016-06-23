package com.moox.system.shiro;

import com.moox.system.dao.UserDao;
import com.moox.system.entity.Resources;
import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 自定义CasRealm,进行数据源配置，单点登录时使用
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 */
public class MyCasRealm extends CasRealm {
    @Autowired
    private UserDao userDao;

    /**
     * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次. 2015年10月27日
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("----------CasRealm验证权限------------------");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取登录时输入的用户名
        String loginName = (String) principals.getPrimaryPrincipal();
        // 到数据库查是否有此对象
        User user = userDao.getUserByloginName(loginName);
        if (user != null) {
            if (user.getId() == 1L) {// 超级管理员 始终拥有所有权限
                info.addStringPermission("*");
            }
            List<Role> roles = user.getRoles();
            HashSet hs = new LinkedHashSet();
            for (Role role : roles) {
                String roleKey = role.getKey();
                hs.add(roleKey);
                // 角色对应相应的权限
                List<Resources> res = role.getResources();
                for (Resources resources : res) {
                    info.addStringPermission(resources.getKey().toString());
                }
            }
            info.setRoles(hs);
        }
        return info;
    }
}
