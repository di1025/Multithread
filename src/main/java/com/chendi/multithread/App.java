package com.chendi.multithread;


class Worker implements Runnable{

//    private boolean isTerminated = false;
    private volatile boolean isTerminated = false;// make sure the var is read from the main CPU memeory


    public void run() {
        while(!isTerminated){
            System.out.println("Hello from worker class..");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        this.isTerminated = terminated;
    }
}
class Runner1 extends Thread {

    public void run() {
        for(int i=0;i<20;i++){
            System.out.println("Runner1: "+i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Runner2 extends Thread {

    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Runner2: "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {

    public static void main(String[] args){
        Thread t1= new Thread(new Runner1());
        Thread t2= new Thread(new Runner2());

//        Runner1 t1 = new Runner1();
//        Runner2 t2 = new Runner2();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished tasks..");


//        Worker worker = new Worker();
//        Thread t3 = new Thread(worker);
//        t3.start();
//
//        try {
//            Thread.sleep(3000);//defined how many times are looped, based on this sleep time and worker run() sleep time.
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        worker.setTerminated(true);
//        System.out.println("Thread 3 Finished...");

    }
}
