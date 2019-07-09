package com.chendi.multithread;


import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * it's an unbounded BlockingQueue of objects that implement the---- Delayed interface
 *
 * an object can only be taken from the queue when its delay has expired
 *
 * no null in queue
 *
 * queue is sorted
 *
 * poll() if no delay has expired, it will return null;
 *
 * size() includes both expired and unexpired items
 *
 * -------DelayQueue only could have the objects which implement the Delayed interface
 *
 */
public class DelayQueueExe {
    public static void main(String[] args){

        BlockingQueue<DelayedWorker> blockingQueue = new DelayQueue();

        try{
            blockingQueue.put(new DelayedWorker(9000,"This is in the first message..."));
            blockingQueue.put(new DelayedWorker(1000,"This is in the second message..."));
            blockingQueue.put(new DelayedWorker(5000,"This is in the third message..."));
        }
        catch (InterruptedException e ){
            e.printStackTrace();
        }

        while (!blockingQueue.isEmpty()){
            try {
                System.out.println(blockingQueue.take());// take() if all the put() are finished, it will take out the shortest duration item firstly
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class DelayedWorker implements Delayed{

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message){
        this.duration = System.currentTimeMillis()+duration;
        this.message = message;
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(duration-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    public int compareTo(Delayed otherDelayed) {
        if(this.duration< ((DelayedWorker) otherDelayed).getDuration()) return -1;
        if(this.duration>((DelayedWorker) otherDelayed).getDuration()) return 1;
        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public String toString(){
        return this.message;
    }
}