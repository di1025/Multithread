package com.chendi.multithread;



class Runner1 extends Thread {

    public void run() {
        for(int i=0;i<100;i++){
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
//        Thread t1= new Thread(new Runner1());
//        Thread t2= new Thread(new Runner2());

        Runner1 t1 = new Runner1();
        Runner2 t2 = new Runner2();

        t1.start();
        t2.start();

    }
}
