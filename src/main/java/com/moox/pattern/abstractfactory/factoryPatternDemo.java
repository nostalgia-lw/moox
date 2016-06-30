package com.moox.pattern.abstractfactory;

/**
 * 抽象工厂模式测试
 * Created by Administrator on 2016-06-29.
 */
public class factoryPatternDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //抽象工厂模式测试
        FatoryInteface fatoryInteface =new BaoMaCarFactory();
        fatoryInteface.createFactory().create();



    }
}
