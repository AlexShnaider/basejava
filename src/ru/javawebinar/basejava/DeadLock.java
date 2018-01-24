package ru.javawebinar.basejava;

public class DeadLock {
    private static final Object LOCK_0 = new Object();
    private static final Object LOCK_1 = new Object();

    public static void main(String[] args) {
        Thread thread0 = new Thread(() -> {
            synchronized (LOCK_0) {
                System.out.println("First thread is started with LOCK_0. Waiting for LOCK_1");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_1) {
                    System.out.println("Thread is stuck");
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (LOCK_1) {
                System.out.println("Second thread is started with LOCK_1. Waiting for LOCK_0");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_0) {
                    System.out.println("Thread is stuck");
                }
            }
        });
        thread0.start();
        thread1.start();
    }
}