package com.chendi.multithread.DiningPhilosopherProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * assign locks on each chopstick
 */

public class DiningPhilosopher {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = null;
        Philosopher[] philosophers = null;

        try{
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];//create an array of philosophers
            Chopstick chopsticks[] = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

            for(int i=0;i<Constants.NUMBER_OF_CHOPSTICKS;i++)
                chopsticks[i] = new Chopstick(i); //initialize the chopsticks array

            executorService= Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);// assign threads to every single philosopher

            for(int i=0;i<Constants.NUMBER_OF_PHILOSOPHERS;i++){
                philosophers[i]= new Philosopher(i,chopsticks[i],chopsticks[(i+1) % Constants.NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for(Philosopher p:philosophers){
                p.setFull(true);
            }
        } finally {
            executorService.shutdown();

            while(!executorService.isTerminated()){
                Thread.sleep(1000);
            }

            for(Philosopher p:philosophers){
                System.out.println(p+" eats "+ p.getEatingCounter());
            }

        }

    }
}
