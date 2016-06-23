package com.moox.system.shiro;

import com.moox.system.dao.UserDao;
import com.moox.system.entity.Resources;
import com.moox.system.entity.Role;
import com.moox.system.entity.User;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 自定义Realm,进行数据源配置
 * 
 * @author tanghom <tanghom@qq.com> 2015-11-18
 */
public class UserRealm extends AuthorizingRealm {
    private static final Logger LOG = Logger.getLogger(UserRealm.class);
    @Autowired
    private UserDao userDao;


    /**
     * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次. 2015年10月27日
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOG.info(">>>>>>>>>>>>验证权限>>>>>>>>>>>>>");
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
                    if(resources.getIsHide()==0){
                        info.addStringPermission(resources.getKey().toString());
                    }
                }
            }
            System.out.println(info.getStringPermissions());
            info.setRoles(hs);
        }
        return info;
    }

    /**
     * 认证回调函数,登录时调用
     * 首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
     * 如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
     * 交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
     * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
     * 另外如果密码重试此处太多将抛出超出重试次数异常ExcessiveAttemptsException；
     * 在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt），
     * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOG.info(">>>>>>>>>>>>认证回调>>>>>>>>>>>>>");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String loginName = upToken.getUsername();
        String pwd = String.valueOf(upToken.getPassword());
        User user = userDao.getUserByloginName(loginName);
        if (null != user) {
            if (user.getLocked() == User.LOCKED) {
                throw new LockedAccountException(); // 帐号锁定
            }
            if (!pwd.equals(user.getLoginPwd())) {
                throw new AuthenticationException(); // 密码不正确
            }
            return new SimpleAuthenticationInfo(loginName, pwd, getName());
        } else {
            throw new UnknownAccountException();// 没找到帐号
        }
    }

    /**
     * 更新用户授权信息缓存.
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 更新用户信息缓存.
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 清除用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 清除用户信息缓存.
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 清空所有认证缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
