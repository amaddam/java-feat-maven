package com.self.lock_type;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2024/1/3
 * @description : 偏向锁
 */
public class BiasedLock {
    public static void main(String[] args) throws InterruptedException {
        //启动线程, 打印出偏向锁的信息, 必须支持偏向锁, jdk21没有偏向锁
        Thread.sleep(5000);
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends Thread {
    static Object object = new Object();
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (object) {
                //打印出偏向锁的信息
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        }
    }
}