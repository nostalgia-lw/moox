package com.moox.pattern.abstractfactory;

/**
 * 宝马汽车工厂
 * Created by Administrator on 2016-06-29.
 */
public class ConcreteFactory2 implements FatoryInteface {
    @Override
    public Car createFactory() {
        return  new BenChiCar();
    }

    @Override
    public Product productFactory() {
        return null;
    }
}
