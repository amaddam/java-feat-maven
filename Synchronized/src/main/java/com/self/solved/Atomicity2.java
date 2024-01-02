package com.self.solved;

import java.util.ArrayList;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/20
 * @description :
 */
public class Atomicity2 {
    static int i = 0;
    static Object object = new Object();


    public static void main(String[] args) throws InterruptedException {
        //为i进行++操作, 5000次
        Runnable thread1 = () -> {
            for (int j = 0; j < 5000; j++) {
                synchronized (object) {
                    i++;
                }
            }
        };

        //开启5个线程, 每个线程对i进行5000次++操作
        ArrayList<Thread> intList = new ArrayList<>();
        for (int i1 = 0; i1 < 5; i1++) {
            Thread thread = new Thread(thread1);
            thread.start();
            intList.add(thread);
        }

        //等待所有线程执行完毕
        for (Thread thread : intList) {
            thread.join();
        }

        System.out.println(i);
    }
}
