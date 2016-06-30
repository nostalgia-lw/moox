package com.moox.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wrj on 2016-06-28.
 */
public class TestReader implements AutoCloseable {
    public static void main(String[] args) {
        TestReader testReader = new TestReader();
        String path = "C:\\Users\\Administrator\\Desktop\\testio.txt";
        testReader.readFileByFileReader(path);
        testReader.readFileByBufferReader(path);
    }

    public void readFileByFileReader(String path){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            char[] buf = new char[2]; //每次读取1024个字符
            int temp = 0;
            System.out.println("readFileByFileReader执行结果：");
            while ((temp = fileReader.read(buf)) != -1) {
                System.out.print(new String(buf, 0, temp));
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //像这种i/o操作尽量finally确保关闭
            if (fileReader!=null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public  void  readFileByBufferReader(String path){
        BufferedReader bufferedReader = null;
        FileReader fileReader =null;
        StringBuffer stringBuffer =new StringBuffer();
        try {
            fileReader =new FileReader(path);
            bufferedReader =new BufferedReader(fileReader);
            char [] buf =new char[1024];
            int temp =0;
            while ((temp=bufferedReader.read(buf,0,buf.length))!=-1){
                stringBuffer.append(new String(buf));
            }
            System.out.println(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void close() throws Exception {

    }
}
