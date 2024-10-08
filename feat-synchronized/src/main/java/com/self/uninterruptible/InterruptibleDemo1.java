package com.self.uninterruptible;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/28
 * @description :
 */
public class InterruptibleDemo1 {
    private static Lock lock = new ReentrantLock();
    private static Lock tryLock = new ReentrantLock();
    private static ReentrantLock lockInterruptibly = new ReentrantLock(false);


    public static void main(String[] args) throws InterruptedException {
        System.out.println("====================演示Lock不可中断====================");
        test01();
        System.out.println("====================演示Lock可中断, tryLock方法====================");
        testTryLock();
        System.out.println("====================演示Lock可中断, lockInterruptibly方法====================");
        testLockInterruptibly();
    }

    // 演示Lock不可中断
    public static void test01() throws InterruptedException {
        Runnable run = () -> {
            String name = Thread.currentThread().getName();
            try {
                lock.lock();
                System.out.println(name + "获得锁,进入锁执行");
                Thread.sleep(88888);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + "释放锁");
            }
        };
        Thread t1 = new Thread(run, "t1");
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(run, "t2");
        t2.start();
        System.out.println("停止t2线程前");
        t2.interrupt();
        System.out.println("停止t2线程后");
        Thread.sleep(1000);
        System.out.println("线程: " + t1.getName() + "的状态为: " + t1.getState());
        System.out.println("线程: " + t2.getName() + "的状态为: " + t2.getState());
    }

    // 演示Lock可中断
    public static void testTryLock() throws InterruptedException {
        Runnable run = () -> {
            String name = Thread.currentThread().getName();
            boolean b = false;
            try {
                b = tryLock.tryLock(3, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(name + "获得锁,进入锁执行");
                    Thread.sleep(88888);
                } else {
                    System.out.println(name + "在指定时间没有得到锁做其他操作");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (b) {
                    tryLock.unlock();
                    System.out.println(name + "释放锁");
                }
            }
        };
        Thread t1 = new Thread(run, "t1");
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(run, "t2");
        t2.start();
    }

    public static void testLockInterruptibly() {
        Runnable thread = () -> {
            try {
                lockInterruptibly.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "获得锁,进入锁执行");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
                //执行其他操作
                e.printStackTrace();
            } finally {
                lockInterruptibly.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁");
            }
        };
        Thread thread1 = new Thread(thread, "thread1");
        Thread thread2 = new Thread(thread, "thread2");

        thread1.start();
        thread2.start();

        // 在一段时间后中断线程1
        try {
            Thread.sleep(1000);
            thread1.interrupt(); // 中断线程1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
