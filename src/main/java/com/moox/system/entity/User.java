package com.moox.system.entity;

import com.moox.system.support.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户
 * Created by wrj on 2016-06-22.
 */
@Entity
@Table(name="s_user")
public class User extends BaseEntity{
    /**
     * 姓名
     */
    @Column(name="name",length = 20)
    private String name;
    /**
     * 登录名
     */
    @Column(name = "login_username",length = 20)
    private String  loginName;
    /**
     * 登录密码
     */
    @Column(name="login_password",length = 50)
    private String  loginPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
