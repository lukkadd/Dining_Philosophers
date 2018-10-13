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
public class Philosopher implements Runnable {

    //Forks available for this Philosopher
    private Object leftFork;
    private Object rightFork;
    
    //Philosopher constructor assigns the forks
    public Philosopher(Object leftFork,Object rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }
    
    private void doAction(String action) throws InterruptedException{
        //Print the action
        System.out.println(
            Thread.currentThread().getName() + " " + action
        );
        //spend a random amount of time doing it
        Thread.sleep((long) (Math.random()*100));
    }
    
    @Override
    public void run (){
        /*
            The philosopher should follow this protocol
            in an infinite loop:
            
        think();
        pick_left_fork();
        pick_right_fork();
        eat();
        put_down_left_fork();
        put_down_right_fork();
        */
        try{
            while(true){
                
                //think
                doAction(System.nanoTime() + ": Thinking");
                
                //pick_up_left_fork
                //synchronized makes sure only this thread will have access
                //to this specific fork while the Philosopher is using it
                synchronized(leftFork){
                    doAction(System.nanoTime() + ": Picked up left fork");
                
                    //pick_up_right_fork
                    synchronized(rightFork){
                        doAction(System.nanoTime() + ": Picked up right fork");
                        
                        //eat
                        doAction(System.nanoTime() + ": Eating");
                    }
                    
                    //put_down_right_fork
                    doAction(System.nanoTime() + ": Put down right fork");
                }
                
                //put_down_left_fork
                doAction(System.nanoTime() + ": Put down left fork");
            }            
        } catch(InterruptedException e){
            System.out.println("Something went wrong... " + Thread.currentThread().getName() + " Interrupted");
            Thread.currentThread().interrupt();
            return;
        }
    }
    
}
