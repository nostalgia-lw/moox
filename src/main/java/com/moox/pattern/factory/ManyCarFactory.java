package com.moox.pattern.factory;

/**
 * 简单多个工厂模式
 * Created by Administrator on 2016-06-29.
 */
public class ManyCarFactory {
    public  Car createBaoMa(){
        return  new BaoMaCar();
    }
    public  Car createBenChi(){
        return  new BenChiCar();
    }
}
