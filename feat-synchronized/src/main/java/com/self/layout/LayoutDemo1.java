package com.self.layout;

import com.self.layout.instance.ObjectHeaderTest;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2024/1/3
 * @description :
 */
public class LayoutDemo1 {
    public static void main(String[] args) {
        ObjectHeaderTest objectHeaderTest = new ObjectHeaderTest();
        Integer integer = objectHeaderTest.hashCode();
        System.out.println(Integer.toHexString(integer));
        ClassLayout classLayout = ClassLayout.parseInstance(objectHeaderTest);
        System.out.println("打印对象头信息: \n" + classLayout.toPrintable());
    }
}
