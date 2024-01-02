package com.self.reentrant;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/28
 * @description :可重入特性: 一个线程获取了某个对象的锁,再次请求该对象锁时是可以再次获取该对象的锁的
 */
public class ReentrantDemo1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("t1");
        myThread.start();

        MyThread myThread2 = new MyThread();
        myThread2.setName("t2");
        myThread2.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        synchronized (this.getClass()) {
            System.out.println(getName() + "进入同步代码块1");
            synchronized (this.getClass()) {
                System.out.println(getName() + "进入同步代码块2");

            }
        }
    }
}