package com.chendi.multithread;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader{

    INSTANCE;

    private Semaphore semaphore = new Semaphore(3,true);//up to 3 thread are available at one time

    public void downloadData(){
        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class SemaphoreEx {
    //maintains a set of permits;
    //acquire() if a permit is available, then takes the lock
    //release() release the lock
    //new Semaphore( int permits, boolean fair) keeps a count of the number available,true->the longest waiting time thread will get the lock

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            executorService.execute(new Runnable() {
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }

    }

}

