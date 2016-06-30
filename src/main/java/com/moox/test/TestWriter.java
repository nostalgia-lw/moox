package com.moox.test;

import java.io.FileWriter;
import java.io.Flushable;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2016-06-29.
 */
public class TestWriter implements  AutoCloseable,Flushable {

    public static void main(String[] args) {
        String path = "C:\\Users\\Administrator\\Desktop\\testio.txt";
        TestWriter.writeFileByFileWriter(path);
    }
    public static void  writeFileByFileWriter(String path){
        FileWriter fileWriter =null;
        try
        {
            fileWriter =new FileWriter(path);
            System.out.print("请输入要打印的值");
            Scanner scanner = new Scanner(System.in);
            String str =scanner.nextLine();
            System.out.print("输入的内容是："+str);
            fileWriter.write(str);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        System.out.print("io close");
    }

    @Override
    public void flush() throws IOException {
        System.out.print("io flush");
    }
}
