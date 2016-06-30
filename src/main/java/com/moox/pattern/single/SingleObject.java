package com.moox.pattern.single;

/**
 * 单例模式-饿汉式
 * Created by Administrator on 2016-06-29.
 */
public class SingleObject {
    private static SingleObject  singleObject= new SingleObject();
    private SingleObject(){};
    public  static SingleObject getInstance(){
        return  singleObject;
    }
}
