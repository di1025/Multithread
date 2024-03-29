package com.chendi.multithread;

import java.util.concurrent.Exchanger;

/**
 * exchange objects between two threads
 */
class FirstThread implements Runnable{
    private int counter;
    private Exchanger<Integer> exchanger;

    //exchanger is an input of constructor;
    public FirstThread(Exchanger<Integer> exchanger){
        this.exchanger=exchanger;
    }

    public void run() {
        while(true){
            counter= counter+1;
            System.out.println("FirstThread incremented the counter "+counter);
            try {
                counter=exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable{

    private int counter;
    private Exchanger<Integer> exchanger;

    //exchanger is an input of constructor;
    public SecondThread(Exchanger<Integer> exchanger){
        this.exchanger=exchanger;
    }

    public void run() {
        while(true){
            counter= counter-1;
            System.out.println("SecondThread decremented the counter "+counter);
            try {
                counter=exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class ExchangerExe {

    public static void main(String[] args) {


        Exchanger<Integer> exchanger = new Exchanger<Integer>();

        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();

    }


}


