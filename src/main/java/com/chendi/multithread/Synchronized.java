package com.chendi.multithread;

public class Synchronized {

    private static int counter =0;

    public static synchronized void increment(){
        counter++;
    }

    public static void process(){

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for( int i=0;i<100;++i)
                    increment();
                //counter++;
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for( int i=0;i<100;++i)
                    increment();
                //counter++;
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

    public static void main(String[] args){
        process();
        System.out.println(counter);
    }
}
