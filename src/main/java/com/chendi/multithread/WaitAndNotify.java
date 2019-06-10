package com.chendi.multithread;

 class Processor {

    public void produce() throws InterruptedException {

        synchronized (this) {// synchronized on the class itself(same object with method consumer)
            System.out.println("In the producer method...");
            wait();//let this method to wait
            System.out.println("Again producer method...");
        }


    }

    public void consumer() throws InterruptedException {

        Thread.sleep(1000);
        synchronized (this) {// synchronized on the class itself(same object with method producer)
            System.out.println("Consumer method...");
            notify();//notify the waiting thread to continue but if there is any code after this thread, this method will finish running all the code firstly.
        }
    }
}

public class WaitAndNotify{
    public static void main(String[] args){

        final Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.consumer();
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

