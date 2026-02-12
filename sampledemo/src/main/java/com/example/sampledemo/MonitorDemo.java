package com.example.sampledemo;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Created by 颜群
 */
public class MonitorDemo {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private static final int MAX = 10;
    //记录生产的数据编号
    private static AtomicInteger count = new AtomicInteger(0);
    //int i ;  i++ ;非原子性操作 ;  i++ 拆分成多行 ；线程安全问题
    private Monitor monitor = new Monitor();

    //生产数据
    public void produce(int value) {
        try {
            //enterWhen：相当于加锁
            monitor.enterWhen(monitor.newGuard(() -> buffer.size() < MAX));
            buffer.addLast(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            monitor.leave();
            System.out.println("生产完毕，缓冲区大小：" + buffer.size());
        }
    }

    //消费数据
    public int consume() {
        try {
            monitor.enterWhen(monitor.newGuard(() -> !buffer.isEmpty()));
            return buffer.removeFirst();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            monitor.leave();
            System.out.println("消费完毕，缓冲区大小：" + buffer.size());
        }
    }

    public static void main(String[] args) {
        MonitorDemo demo = new MonitorDemo();

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        //将线程池包装
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        //向线程池放入3个生产者
        for (int i = 0; i < 3; i++) {
            listeningExecutorService.submit(() -> {
                while (true) {
                    int result = count.getAndIncrement(); //i++  0  1  2
                    demo.produce(result);
                    System.out.println("生产：" + result);
                }

            });
        }

        //向线程池放入3个消费者
        for (int i = 0; i < 3; i++) {
            listeningExecutorService.submit(() -> {
                while (true) {
                    int result = demo.consume();
                    System.out.println("消费" + result);
                }
            });
        }
    }
}
