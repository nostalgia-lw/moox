package com.moox.pattern.factory;

/**
 * 工厂模式
 * Created by Administrator on 2016-06-29.
 */
public class CarFactory {
    public  Car  createCar(String brandName){
        Car car =null;
        if(brandName.equals("BaoMa")){
            car =new BaoMaCar();
        }else  if(brandName.equals("BenChi")){
            car =new BenChiCar();
        }
        return car;
    }


}
