package com.moox.pattern.abstractfactory;

/**
 * 工厂模式测试
 * Created by Administrator on 2016-06-29.
 */
public class factoryPatternDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //工厂模式测试
        FatoryInteface fatoryInteface =new ConcreteFactory();
        fatoryInteface.createFactory().create();



    }
}
