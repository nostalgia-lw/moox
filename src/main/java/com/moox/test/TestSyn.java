package com.moox.test;

/**
 * sys用法
 * Created by wrj on 2016-07-01.
 */
public class TestSyn {
    public static void main(String args[]) throws InterruptedException{
        TestSyn TestSyn = new TestSyn();
        TestSyn.test();
    }

    public void test() throws InterruptedException{
       for (int i=0;i<100;i++){
           //new SynThread1().start();//这里创建线程，使用多线程来执行方法
           try {
               new  TestSyn().forward("wrj");//这里默认使用单线程来执行方法
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }

    public  void  forward(String userName) throws Exception {
       // synchronized(userName) { 同步关键字可以这样包代码块包起来，也可以加在方法上，synchronized 需要配合多线程使用才能体现它的意义
            int a=0;
            System.out.println("进入到同步块，userName=" + userName+",当前的线程 "+Thread.currentThread().getName());
            a++;
            Thread.sleep(100);  //5秒
            System.out.println("   退出同步块，userName=" + userName+",当前的线程 "+Thread.currentThread().getName()+",a的值为:"+a);
       // }
    }

    class SynThread1 extends Thread {
        public void run(){
            try {
                forward("wrj");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
