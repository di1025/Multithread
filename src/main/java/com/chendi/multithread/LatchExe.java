package com.chendi.multithread;

//countdown latch is to synchronize one or more tasks by forcing them to wait for the completion of a set of operations being performed
// by other tasks.

//the tasks calls countDown() are not blocked when they make that call, only the call to await() is blocked until the count reaches 0;

//eg: do something after 1000 users download image;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchExe {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CountDownLatch latch = new CountDownLatch(5);//specify the count down number, it will be blocked until it is called 5 times

        for(int i=0;i<5;i++)
            executorService.execute(new Worker3(i+1,latch));

        try {
            latch.await();// make sure every single worker is going to finish its own job.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All the prerequisites are done...");// will be printed out after executed 5 times which is specified in line 21
        executorService.shutdown();
    }

}


class Worker3 implements Runnable{

    private int id;
    private CountDownLatch countDownLatch;

    public Worker3(int id, CountDownLatch countDownLatch){
        this.id=id;
        this.countDownLatch=countDownLatch;
    }

    public void run() {
        doWork();
        countDownLatch.countDown();
    }


    private void doWork() {
        System.out.println("Thread with id "+ id+ " starts working");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}