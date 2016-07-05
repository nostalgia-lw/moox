package com.moox.test;

/**
 * Created by Administrator on 2016-06-27.
 */
public class TestThread extends  Thread {
    private  String name;
    private  Integer ticketNum =10;
    public  TestThread(){};
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
    public    void  testSynchroinzed(){
        try {
          System.out.println("运行中");
            Thread.sleep(5000);
            System.out.println("运行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestThread ta =new TestThread();
         ta.testSynchroinzed();
        // ta.testSynchroinzed();
        TestThread tb =new TestThread();
        tb.testSynchroinzed();
        //tb.testSynchroinzed();

    }
}
