package ru.javawebinar.basejava;

public class DeadLock {
    private static final String LOCK_0 = new String("LOCK_0");
    private static final String LOCK_1 = new String("LOCK_1");

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        Thread thread0 = new Thread(() -> {
            deadLock.syncronizedMethod(LOCK_0,LOCK_1);
        });
        Thread thread1 = new Thread(() -> {
            deadLock.syncronizedMethod(LOCK_1,LOCK_0);

        });
        thread0.start();
        thread1.start();
    }

    private void syncronizedMethod(String lock0, String lock1) {
        synchronized (lock0) {
            System.out.println(Thread.currentThread().getName() + " is started with " + lock0 + ". Waiting for " + lock1);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.println("Thread is stuck");
            }
        }
    }
}