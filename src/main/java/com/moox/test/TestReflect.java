package com.moox.test;

import com.moox.system.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created  by wrj on 2016-06-28.
 */
public class TestReflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
       User user = new User();
       Class  class1 =user.getClass();
       Class calss2 = User.class;
        Class clazz3 =Class.forName("com.moox.system.entity.User");
        Method [] methods =calss2.getMethods();
        Field [] fields =clazz3.getDeclaredFields();
        /*for (Method method:methods) {
            System.out.println(method.getName());
        }*/
        for (Field field :fields){
            System.out.println(field.getName());
        }

    }
    public  void  fun(String str){
        System.out.println("输出的字符串是："+str);
    }
}
