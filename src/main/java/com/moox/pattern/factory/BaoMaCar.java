package com.moox.pattern.factory;

/**
 * Created by Administrator on 2016-06-29.
 */
public class BaoMaCar implements  Car {
    @Override
    public void create() {
        System.out.print("生成宝马小汽车");
    }
}
