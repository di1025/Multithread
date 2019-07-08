package com.chendi.multithread;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsExe {
    //1. ExecutorService exe =  Executors.newCachedThreadPool();
    //   reuse threads: check if there is any thread is in the waiting state and then reuse it,
    //* if all the thread are busy, it will create threads

    //2. ExecutorService exe =  Executors.newCachedThreadPool(int maxNum);
    //* if all the thread are busy, it will wait for one thread to terminate

    //3. ExecutorService exe = Executors.newSingleThreadExecutor();
    // it uses a single thread for the job
    //exe.submit()->runnable+ callable
    //exe.execute()->runnable

    public static void main(String[] args){

//        ExecutorService executorService = Executors.newFixedThreadPool(3);// 3 threads at the same time, then 2 threads, total is 5

        ExecutorService executorService =Executors.newSingleThreadExecutor();// only one thread processes, for 5 times

        for(int i=0;i<5;i++){
            executorService.submit(new Worker2());// i from 0-9 was executed 5 times
        }
    }

}

class Worker2 implements Runnable{

    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
