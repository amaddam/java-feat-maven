package com.self.lock_type;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2024/1/3
 * @description :
 */
public class EliminationLockDemo {
    public static void main(String[] args) {
        contactString("1", "2");
    }

    private static String contactString(String number1, String number2) {
        return new StringBuffer(number1).append(number2).toString();
    }
}
