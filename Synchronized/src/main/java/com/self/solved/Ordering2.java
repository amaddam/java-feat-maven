package com.self.solved;

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
@JCStressTest
@State
@Outcome(id = {"1", "4"}, expect = Expect.ACCEPTABLE, desc = "这是期待的结果")
@Outcome(id = "0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "这是不期待的结果")
public class Ordering2 {

    private int a = 0;
    private boolean flag = false;
    private Object object = new Object();
    @Actor
    public void method(I_Result result) {
        synchronized (object) {
            if (flag) {
                result.r1 = a * 2;
            } else {
                result.r1 = 1;
            }
        }
    }

    @Actor
    public void method2(I_Result result) {
        synchronized (object) {
            a = 2;
            flag = true;
        }
    }
}