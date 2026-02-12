package other;

import java.util.TreeSet;

public class DeadLock {

    static class Person {}

    public static void main(String[] args) {

        new Thread(new MyRunnable(false), "knife_forks").start();// knife_forks
        new Thread(new MyRunnable(true), "chopsticks").start();// chopsticks

    }

    static class MyRunnable implements Runnable {

        static final Object chopsticks = new Object();
        static final Object knife_forks = new Object();

        boolean flag;

        MyRunnable(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                synchronized (chopsticks) {
                    System.out.println(Thread.currentThread().getName() + "获得了chopsticks的锁！");
                    synchronized (knife_forks) {
                        System.out.println(Thread.currentThread().getName() + "获得了knife_forks的锁！");
                    }
                }
            } else {
                synchronized (knife_forks) {
                    System.out.println(Thread.currentThread().getName() + "获得了knife_forks的锁！");
                    synchronized (chopsticks) {
                        System.out.println(Thread.currentThread().getName() + "获得了chopsticks的锁！");
                    }
                }
            }
        }
    }

}
