package com.moox.pattern.proto;

import java.io.*;

/**
 * 原型模式
 * Created by Administrator on 2016-06-29.
 */
public class ProtoType implements  Cloneable,Serializable {

    private static final long serialVersionUID = 2136268073279821810L;
    private int base;

    private Integer obj;

    /* 浅复制 */
    public Object clone() throws CloneNotSupportedException {
        // 因为Cloneable接口是个空接口，你可以任意定义实现类的方法名
        // 如cloneA或者cloneB，因为此处的重点是super.clone()这句话
        // super.clone()调用的是Object的clone()方法
        // 而在Object类中，clone()是native（本地方法）的
        ProtoType proto = (ProtoType) super.clone();
        return proto;
    }

    /* 深复制 */
    public Object deepClone() throws IOException, ClassNotFoundException {

        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }


    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public Integer getObj() {
        return obj;
    }

    public void setObj(Integer obj) {
        this.obj = obj;
    }
}

