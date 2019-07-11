package com.chendi.multithread.LibrarySimulation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

    private int id;
    private Lock lock;

    public Book(int id){
        this.id=id;
        this.lock=new ReentrantLock();
    }
    public void borrowed(Student student) throws  InterruptedException{
        //tryLock() will return boolean immediately and will not wait
//        if(lock.tryLock(10, TimeUnit.MILLISECONDS)){
        //lock() will wait
        lock.lock();
        System.out.println(student+" borrowed " + this);
        Thread.sleep(5000);
        lock.unlock();
        System.out.println(student+" returned " + this);
    }

    @Override
    public String toString() {
        return "Book " +
                "id=" + id ;
    }
}
