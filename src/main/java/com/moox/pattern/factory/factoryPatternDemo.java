package com.moox.pattern.factory;

/**
 * 工厂模式测试
 * Created by Administrator on 2016-06-29.
 */
public class factoryPatternDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //工厂模式测试
        Class clazz =CarFactory.class;
        CarFactory carFactory = (CarFactory) clazz.newInstance();
        Car car = carFactory.createCar("BenChi");
        car.create();

        //多个工厂测试
        Class clazz2 =ManyCarFactory.class;
        ManyCarFactory manyCarFactory = (ManyCarFactory) clazz2.newInstance();
        Car car1 = manyCarFactory.createBaoMa();
        car1.create();

        //静态工厂模式
        Car car2 = StaticFactory.createBenChi();
        car2.create();


    }
}
