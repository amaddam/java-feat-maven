package com.self.lock_type;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2024/1/3
 * @description :
 */
public class CoarsingLockdDemo {
    public static void main(String[] args) {
        synchronized (CoarsingLockdDemo.class) {
            System.out.println("hello world");
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            stringBuffer.append(i);
        }
        System.out.println(stringBuffer);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(12);
    }
}
