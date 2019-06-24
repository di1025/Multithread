package com.chendi.multithread;

import java.util.ArrayList;
import java.util.List;

class Processor2 {

    private List<Integer> list = new ArrayList<Integer>();
    private final int LIMIT = 5;
    private final int BOTTOM =0;
    private final Object lock = new Object();//make sure the whole class will not wait
    private int value =0;

    public void producer() throws InterruptedException {

        synchronized (lock){
            while(true){
                if(list.size()==LIMIT){
                    System.out.println("Hit the limit and Waiting for removing items from the list...");
                    lock.wait();
                }else{
                    System.out.println("Adding : "+ value);
                    list.add(value++);
                    lock.notify();
                }
            }
        }

    }

    public void consumer() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(list.size()==BOTTOM){
                    System.out.println("Hit the botten and waiting for adding items to the list..." );
                    lock.wait();
                }
                else {
                    System.out.println("Removing: "+list.remove(--value));
                    lock.notify();
                }
                Thread.sleep(100);
            }
        }

    }
}

public class ProducerConsumer {
    static Processor2 processor2 = new Processor2();

    public static void main(String[] arg){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                    try {
                        processor2.producer();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor2.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();


    }
}
