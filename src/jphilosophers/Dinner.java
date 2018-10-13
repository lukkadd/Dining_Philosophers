/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jphilosophers;

/**
 *
 * @author lukka
 */
public class Dinner {
    public static void main(String[] args) {
        
        //initializing philosopher and fork arrays
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[5];
        
        //initializing Fork objects
        for(int i = 0; i < forks.length; i++){
            forks[i] = new Object();
        }
        
        //initializing Philosopher objects
        for(int i = 0; i < philosophers.length; i++){
            philosophers[i] = new Philosopher(
                    forks[i],
                    forks[(i+1) % forks.length]
            );
            
            //creating the philosopher thread and running it
            Thread t = new Thread(philosophers[i],"Philosopher " + (i+1));
            t.start();
        }
        
    }
   
}
