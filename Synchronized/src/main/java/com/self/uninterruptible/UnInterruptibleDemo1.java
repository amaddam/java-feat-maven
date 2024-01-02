package com.self.uninterruptible;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/28
 * @description :不可中断特性: 一个线程获取了某个对象的锁,在没有释放该锁之前,其他线程是无法获取该对象的锁的
 */
public class UnInterruptibleDemo1 {
    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 1.定义一个Runnable
        Runnable run = () -> {
            // 2.在Runnable定义同步代码块
            synchronized (obj) {
                String name = Thread.currentThread().getName();
                System.out.println(name + "进入同步代码块");
                // 保证不退出同步代码块
                try {
                    Thread.sleep(888888);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 3.先开启一个线程来执行同步代码块
        Thread t1 = new Thread(run, "t1");
        t1.start();
        Thread.sleep(1000);
        // 4.后开启一个线程来执行同步代码块(阻塞状态)
        Thread t2 = new Thread(run, "t2");
        t2.start();
        // 5.停止第二个线程
        System.out.println("停止线程前");
        t2.interrupt();
        System.out.println("停止线程后");
        System.out.println("线程: " + t1.getName() + "的状态为: " + t1.getState());
        System.out.println("线程: " + t2.getName() + "的状态为: " + t2.getState());

    }
}
