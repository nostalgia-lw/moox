package com.moox.pattern.factory;

/**
 * 静态工厂
 * Created by wrj on 2016-06-29.
 */
public class StaticFactory {
    public static   Car createBaoMa(){
        return  new BaoMaCar();
    }
    public  static Car createBenChi(){
        return  new BenChiCar();
    }
}
