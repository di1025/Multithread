package com.chendi.multithread.LibrarySimulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App   {

    public static void main(String[] args){

        Student[] students = null;
        Book[] books = null;
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.STUDENT_NUMBER);

        try{
            books = new Book[Constants.BOOK_NUMBER];
            students = new Student[Constants.STUDENT_NUMBER];

            for(int i=0;i<Constants.BOOK_NUMBER;i++)
                books[i] = new Book(i);

            for(int i=0;i<Constants.STUDENT_NUMBER;i++){
                students[i] = new Student(i,books);
                executorService.execute(students[i]);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
