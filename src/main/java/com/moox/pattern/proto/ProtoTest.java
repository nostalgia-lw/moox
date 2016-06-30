package com.moox.pattern.proto;

import java.io.IOException;

/**
 * Created by Administrator on 2016-06-29.
 */
public class ProtoTest {
    public static void main(String[] args) throws CloneNotSupportedException,
            ClassNotFoundException, IOException {
        ProtoType prototype = new ProtoType();
        prototype.setBase(1);
        prototype.setObj(new Integer(2));
        /* 浅复制 */
        ProtoType prototype1 = (ProtoType) prototype.clone();
        /* 深复制 */
        ProtoType prototype2 = (ProtoType) prototype.deepClone();
        System.out.println(prototype1.getObj()==prototype1.getObj());
        System.out.println(prototype1.getObj()==prototype2.getObj());
    }

}
