package com.self.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/30
 * @description :
 */
public class ConcurrentTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                10,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                //默认线程工厂
                Executors.defaultThreadFactory(),
                //默认拒绝策略, 直接抛出异常
                new ThreadPoolExecutor.AbortPolicy()
                //拒绝策略, 使用调用者线程执行
                // new ThreadPoolExecutor.CallerRunsPolicy()
                //拒绝策略, 丢弃队列中最老的任务, 将当前任务加入队列

                // new ThreadPoolExecutor.DiscardOldestPolicy()
                //拒绝策略, 丢弃当前任务
                // new ThreadPoolExecutor.DiscardPolicy()
        );
    }
}
