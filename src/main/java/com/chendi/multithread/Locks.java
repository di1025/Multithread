package com.chendi.multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    private static int counter =0;

    private static Lock lock = new ReentrantLock();// ReentrantLock is an implementation of Lock interface, when true/fair, it follows time order.

    public static void increment(){
        lock.lock();
        try{
            for(int i=0;i<1000;i++)
                counter++;
        }finally {
            lock.unlock();//unlock() must be in the finally block
        }
    }

    public static void main (String[] args){

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                increment();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter is "+counter);
    }
}
