package com.self.self;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * @author : lrns1
 * @version : 1.0
 * @date : Created in 2023/12/23
 * @description :
 */
//JCStressTest注解的作用是声明这是一个JCStress测试, 会
// @JCStressTest
// @Outcome(id = {"1", "4"}, expect = Expect.ACCEPTABLE, desc = "ok")
// @Outcome(id = "0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "danger")
// @State
// public class Ordering1 {
//     int num = 0;
//     boolean ready = false;
//
//     //线程1执行的代码
//     @Actor
//     public void thread1(I_Result r) {
//         if (ready) {
//             r.r1 = num + num;
//         }else {
//             r.r1 = 1;
//         }
//     }
//
//     //线程2执行的代码
//     @Actor
//     public void thread2(I_Result r) {
//         num = 2;
//         ready = true;
//     }
// }

@JCStressTest
@State
@Outcome(id = {"1", "4"}, expect = Expect.ACCEPTABLE, desc = "这是期待的结果")
@Outcome(id = "0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "这是不期待的结果")
public class Ordering1 {

    /**
     * 结果有三种可能 4， 1，0(这个0是因为指令重排）
     */
    private int a = 0;
    private boolean flag = false;
    @Actor
    public void method(I_Result result) {
        if (flag) {
            result.r1 = a * 2;
        } else {
            result.r1 = 1;
        }
    }

    @Actor
    public void method2(I_Result result) {
        a = 2;               //这个地方有可能会发生指令重排，也就是a=2和flag=true互换
        flag = true;
    }

}