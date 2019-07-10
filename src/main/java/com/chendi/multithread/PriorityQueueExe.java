package com.chendi.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

// PriorityQueue is a implementation of BlockingQueue, if need to sort by self-define, the objects in the queue should implement comparable interface



class FirstWorker1 implements Runnable{

    private BlockingQueue<Person> blockingQueue;

    public FirstWorker1(BlockingQueue<Person> blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    public void run() {
        try {
            blockingQueue.put(new Person(10,"Blair"));
            blockingQueue.put(new Person(12,"Ray"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(9,"Adam"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(11,"Aria"));
            blockingQueue.put(new Person(19,"Emma"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker1 implements Runnable{

    private BlockingQueue<Person> blockingQueue;

    public SecondWorker1(BlockingQueue<Person> blockingQueue){
        this.blockingQueue=blockingQueue;
    }


    public void run() {
        try {
            Thread.sleep(5000);// sleep 5 secs to make sure all the elements are added into the queue
            System.out.println( blockingQueue.take());
            System.out.println( blockingQueue.take());
            Thread.sleep(1000);
            System.out.println( blockingQueue.take());
            System.out.println( blockingQueue.take());
            Thread.sleep(1000);
            System.out.println( blockingQueue.take());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person>{

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public Person(int age, String name){
        this.name=name;
        this.age=age;
    }

    public int compareTo(Person o) {
        return name.compareTo(o.getName());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return name+" - " +age;
    }
}


public class PriorityQueueExe {

    public static void main(String[] args) {

        BlockingQueue<Person> queue = new PriorityBlockingQueue<Person>();

        new Thread(new FirstWorker1(queue)).start();
        new Thread(new SecondWorker1(queue)).start();
    }

}