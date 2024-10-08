package com.self.cas;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2024/1/2
 * @description :
 */
public class CASDemo1 {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    public static void main(String[] args) {
        //为atomicInteger进行++操作, 5000次
        Runnable thread1 = () -> {
            for (int j = 0; j < 5000; j++) {
                atomicInteger.incrementAndGet();
            }
        };

        //开启5个线程, 每个线程对atomicInteger进行5000次++操作
        List<Thread> intList = List.of(
                new Thread(thread1),
                new Thread(thread1),
                new Thread(thread1),
                new Thread(thread1),
                new Thread(thread1)
        );
        intList.forEach(Thread::start);
        intList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(atomicInteger);
    }
}
