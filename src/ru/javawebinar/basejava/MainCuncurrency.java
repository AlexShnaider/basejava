package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainCuncurrency {
    private static final int THREAD_NUMBER = 10000;
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " State: " + getState());
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " State: " + Thread.currentThread().getState());
            }
        }).start();

        MainCuncurrency mainCuncurrency = new MainCuncurrency();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainCuncurrency.inc();

                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(counter);
    }

    private synchronized void inc() {
        counter++;
    }
}
