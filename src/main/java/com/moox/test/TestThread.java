package com.moox.test;

/**
 * Created by Administrator on 2016-06-27.
 */
public class TestThread extends  Thread {
    private  String name;
    private  Integer ticketNum =10;
    public  TestThread(String name){
        this.name =name;
    }
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println(name+"_线程:"+ticketNum--);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        TestThread ta =new TestThread("A");
        TestThread tb =new TestThread("B");
        ta.start();
        tb.start();
    }
}
