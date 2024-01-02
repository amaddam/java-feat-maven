package com.self.compile;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/29
 * @description :
 */
public class CompileSynchronized {
    private static Object obj = new Object();
    public static void main(String[] args) {
        synchronized (obj) {
            System.out.println("1");
        }
    }
    public synchronized void test() {
        System.out.println("a");
    }

    public static synchronized void test2() {
        System.out.println("b");
    }
}
