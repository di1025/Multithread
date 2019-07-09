package com.chendi.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue is an interface that represents a queue that is thread safe, it wont exceed the max number of object in the queue
 * put(),take()
 * use ArrayBlockingQueue<> to implement
 */


class FirstWorker implements Runnable{

    private BlockingQueue<Integer> blockingQueue;

    public FirstWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    public void run() {
        int counter =0;

        while (true){
            try {
                blockingQueue.put(counter);
                System.out.println("Putting items to queue..."+counter);
                counter++;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class SecondWorker implements Runnable{

    private BlockingQueue<Integer> blockingQueue;

    public SecondWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    public void run() {
        while (true){
            try {
                int number = blockingQueue.take();
                System.out.println("Taking items from queue..."+number);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueueExe {

    public static void main(String[] args){

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);

        FirstWorker firstWorker = new FirstWorker(blockingQueue);
        SecondWorker secondWorker= new SecondWorker(blockingQueue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();


    }
}
