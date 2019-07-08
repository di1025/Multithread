package com.chendi.multithread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor1 implements Callable<String> {//use callable interface if need to return the result.
    private int id;

    public Processor1(int id){
        this.id=id;
    }

    public String call() throws Exception{
        Thread.sleep(1000);
        return "Id: "+ id;
    }
}


public class CallableExe {
    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> list= new ArrayList<Future<String>>();// use Future to work with callable interface

        for(int i=0;i<5;i++){
            Future<String> future = executorService.submit(new Processor1(i+1));
            list.add(future);
        }

        for(Future<String> future:list){
            try {
                System.out.println(future.get());//future.get() is getting the returned results, it needs to be surrounded
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        /**
         * Attempts to stop all actively executing tasks, halts the
         * processing of waiting tasks, and returns a list of the tasks
         * that were awaiting execution.
         * */

    }
}
