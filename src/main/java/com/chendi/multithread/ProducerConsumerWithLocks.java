package com.chendi.multithread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker1{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer( ) throws InterruptedException{
        lock.lock();
        System.out.println("Producer method...");
        condition.await();// same as lock.wait();
        System.out.println("Producer again...");
        lock.unlock(); //lock() method must be paired with unlock() method
    }

    public void consumer() throws InterruptedException{

        lock.lock();
        Thread.sleep(2000);
        System.out.println("Consumer method...");
        condition.signal();//same as lock.notify();
        lock.unlock();
    }
}


public class ProducerConsumerWithLocks {


    public static void main(String[] args){
        final Worker1 worker = new Worker1();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    worker.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    worker.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    }
}
