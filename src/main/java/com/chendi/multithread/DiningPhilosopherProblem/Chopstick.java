package com.chendi.multithread.DiningPhilosopherProblem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private int id;
    private Lock lock;

    public Chopstick(int id){
        this.id=id;
        this.lock= new ReentrantLock();
    }

    public boolean pickUp(State state, Philosopher philosopher) throws InterruptedException{
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {// how frequently to try if this lock is available;
            System.out.println(philosopher+" picked up"+state.toString()+" "+this);// this is going to print out the toString() method return
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, State state){
        lock.unlock();
        System.out.println(philosopher + " put down "+ this);
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id;
    }
}
