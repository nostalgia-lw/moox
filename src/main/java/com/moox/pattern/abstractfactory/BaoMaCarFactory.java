package com.moox.pattern.abstractfactory;

/**
 * 宝马汽车工厂
 * Created by Administrator on 2016-06-29.
 */
public class BaoMaCarFactory implements FatoryInteface {
    @Override
    public Car createFactory() {
        return  new BaoMaCar();
    }
}
