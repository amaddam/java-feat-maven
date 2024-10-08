package com.self.problem;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/20
 * @description :
 */
@Slf4j
public class Visibility1 {
    private static boolean readyNoVolatile = true;

    //线程可见性
    // 内存案例: https://www.zhihu.com/question/349084513/answer/3024609281

    public static void main(String[] args) throws InterruptedException {
        //volatile 只能保证可见性，不能保证在多线程环境下的原子性
        testReadyNoVolatile();
    }

    public static void testReadyNoVolatile() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (readyNoVolatile) {
//                System.out.println("thread1 NoVolatile");
                //加了sout之后, 线程就能看到readyNoVolatile的变化了
            }
        }, "thread1");
        thread1.start();

        Thread.sleep(10);

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 已经将 ready 设置为 false");
            //加了sout之后, 线程就能看到readyNoVolatile的变化了
            readyNoVolatile = false;
        }, "thread2");
        thread2.start();
    }
}