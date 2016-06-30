package com.moox.pattern.abstractfactory;

/**
 * Created by Administrator on 2016-06-29.
 */
public class BenChiCar implements Car {
    @Override
    public void create() {
        System.out.print("生成奔驰汽车");
    }
}
