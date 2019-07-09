package com.chendi.multithread;

//CyclicBarrier is used in situations where want to create a group of tasks to perform work in parallel + wait until
//they are all finished before moving on to the next step

//similar with: join(), CountDownLatch()
//differences: CountDownLatch is one-shot event;
//             CyclicBarrier could be reused over and over again, reset(); it has a barrier action: a runnable will run automatically
//              when the count reaches 0;
//             CyclicBarrier(N) N threads wait for each other


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker4 implements Runnable{

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker4(int id, CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
        this.id=id;
        this.random= new Random();
    }

    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID "+ id +" start the task...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with ID "+ id +" finished");

        try {
            cyclicBarrier.await();
            //if here is some code, it will be executed 5 times since 5 threads defined in line 63;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return ""+ this.id;
    }
}

public class CyclicBarrierExe {
    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {//if 3 threads finished then
            public void run() {             //after 3 tasks are finished(count = 0;), this is going to be run;
                System.out.println("All the tasks are finished");
            }
        });

        for(int i=0;i<3;i++)
            executorService.execute(new Worker4(i+1,barrier));

        executorService.shutdown();

    }
}
