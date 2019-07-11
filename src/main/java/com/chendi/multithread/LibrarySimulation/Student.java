package com.chendi.multithread.LibrarySimulation;

import java.util.Arrays;
import java.util.Random;

public class Student implements Runnable{

    private int id;
    private Book[] books;

    public Student(int id, Book[] books){
        this.id=id;
        this.books=books;
    }


    public void run() {

        Random random = new Random();

        while (true){
            int bookId = random.nextInt(Constants.BOOK_NUMBER);
            try {
                books[bookId].borrowed(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id ;
    }
}
