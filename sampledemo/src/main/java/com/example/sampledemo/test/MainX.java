package com.example.sampledemo.test;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class MainX {
    public static void main(String[] args) {
        LocalDate start = LocalDate.now().minusMonths(1);
        LocalDate end = LocalDate.now();
        System.out.println(start);
        System.out.println(end);
    }

    public static void main1(String[] args) {
// 初始化 AtomicInteger 对象，初始值为 0
        AtomicInteger atomicInt = new AtomicInteger(0);

// 使用 getAndSet 方法获取当前值，并设置新值为 3
        int tempValue = atomicInt.getAndSet(3);
        System.out.println("tempValue: " + tempValue + "; atomicInt: " + atomicInt);

// 使用 getAndIncrement 方法获取当前值，并自增 1
        tempValue = atomicInt.getAndIncrement();
        System.out.println("tempValue: " + tempValue + "; atomicInt: " + atomicInt);

// 使用 getAndAdd 方法获取当前值，并增加指定值 5
        tempValue = atomicInt.getAndAdd(5);
        System.out.println("tempValue: " + tempValue + "; atomicInt: " + atomicInt);

// 使用 compareAndSet 方法进行原子性条件更新，期望值为 9，更新值为 10
        boolean updateSuccess = atomicInt.compareAndSet(9, 10);
        System.out.println("Update Success: " + updateSuccess + "; atomicInt: " + atomicInt);

// 获取当前值
        int currentValue = atomicInt.get();
        System.out.println("Current value: " + currentValue);

// 使用 lazySet 方法设置新值为 15
        atomicInt.lazySet(15);
        System.out.println("After lazySet, atomicInt: " + atomicInt);
    }
}
