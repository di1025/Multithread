package com.chendi.multithread.DiningPhilosopherProblem;

import java.util.Random;

public class Philosopher implements Runnable {

    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;
    private volatile boolean isFull = false;//get the var from the main memory not the cache

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick){
        this.id=id;
        this.leftChopstick=leftChopstick;
        this.rightChopstick= rightChopstick;
        this.random = new Random();
    }


    public void run() {

        try {
            while(!isFull){
                think();
                if(leftChopstick.pickUp(State.LEFT,this)){
                    if(rightChopstick.pickUp(State.RIGHT,this)){
                        eat();
                        rightChopstick.putDown(this,State.RIGHT);
                    }
                    leftChopstick.putDown(this,State.LEFT);
                }
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }


    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    @Override
    public String toString() {
        return "Philosopher " +
                "id=" + id ;
    }

    private void think() throws InterruptedException{
        System.out.println("Philosopher is thinking...");
        Thread.sleep(random.nextInt(1000));
    }
    private void eat() throws InterruptedException{
        System.out.println("Philosopher is eating...");
        this.eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }
    public int getEatingCounter(){
        return eatingCounter;
    }


}
