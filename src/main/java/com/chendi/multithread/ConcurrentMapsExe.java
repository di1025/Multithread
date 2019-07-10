package com.chendi.multithread;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class FirstCoWorker implements Runnable{

    private ConcurrentMap<String, Integer> map;//if concurrently work on the same data, the data structure should as a parameter in constructor

    public FirstCoWorker(ConcurrentMap<String,Integer> map){
        this.map=map;
    }

    public void run() {
        try {

            map.put("W", 1);
            map.put("C", 5);
            Thread.sleep(1000);
            map.put("A", 3);
            map.put("Y", 4);
            Thread.sleep(1000);
            map.put("R", 4);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}


class SecondCoWorker implements Runnable{

    private ConcurrentMap<String, Integer> map;

    public SecondCoWorker(ConcurrentMap<String,Integer> map){
        this.map=map;
    }

    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("Y"));
            System.out.println(map.get("R"));
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}

public class ConcurrentMapsExe {

    public static void main(String[] args){

        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
        new Thread(new FirstCoWorker(map)).start();
        new Thread(new SecondCoWorker(map)).start();
    }
}
