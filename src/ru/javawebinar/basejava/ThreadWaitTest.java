package ru.javawebinar.basejava;

public class ThreadWaitTest implements Runnable {
    private Object shared;

    public ThreadWaitTest(Object o) {
        shared=o;
    }

    public void run() {
        synchronized (shared) {
            try {
                shared.wait();
            } catch (InterruptedException e) {}
            System.out.println("after wait");
        }
    }

    public static void main(String s[]) {
        Object o = new Object();
        ThreadWaitTest w = new ThreadWaitTest(o);
        new Thread(w).start();
        try {
            synchronized (o) {
                o.notifyAll();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {}
        System.out.println("before notify");
        synchronized (o) {
            o.notifyAll();
        }
    }
}