package com.tingfeng.test.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * 测试工具类
 */
public class TestUtils {
    /**
     *
     * @param thread 启动的线程数量
     * @param cycleCountInThread 线程内的循环次数
     * @param function 需要执行的业务逻辑,传入cycleCountInThread中每次循环的下标，从0到(cycleCountInThread-1)的
     */
    public static  void printTime(int thread,int cycleCountInThread,Function<Integer,Object> function) {
        long startTime = System.currentTimeMillis();
        AtomicInteger value = new AtomicInteger(0);
        for(int i = 0 ; i < thread ;i++) {
            new Thread(()->{
                try{
                    try {
                        for(int  j = 0;j < cycleCountInThread ; j++) {
                            function.apply(j);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }finally {
                    value.incrementAndGet();
                }
            }).start();
        }
        while (value.get() < thread){
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("use time:" + (System.currentTimeMillis() - startTime));
    }
}
