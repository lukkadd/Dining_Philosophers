/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jphilosophers;

import processing.core.*;

/**
 *
 * @author lukka
 */
public class PhilosopherV1 implements Runnable {

    //Forks available for this Philosopher
    private Object leftFork;
    private Object rightFork;
    public int state;
    public int posx;
    public int posy;
    private int id;
    
    //Philosopher constructor assigns the forks
    public PhilosopherV1(Object leftFork,Object rightFork,int posx, int posy, int id){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.state = 0;
        this.posx = posx;
        this.posy = posy;
        this.id = id;
    }
    
    private void doAction(String action) throws InterruptedException{
        //Print the action
        System.out.println(
            Thread.currentThread().getName() + " " + action
        );
        //spend a random amount of time doing it
        Thread.sleep((long) (Math.random()*1000));
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
        
        this.id = Integer.parseInt(Thread.currentThread().getName().replaceAll("\\D", "")) - 1;
    
        try{
            while(true){
                
                //think
                GlobalClass.states[this.id] = 0;
                doAction(System.nanoTime() + ": Thinking");
                
                //pick_up_left_fork
                //synchronized makes sure only this thread will have access
                //to this specific fork while the Philosopher is using it
                synchronized(leftFork){
                    GlobalClass.states[this.id] = 1;
                    doAction(System.nanoTime() + ": Picked up left fork");
                    
                    //pick_up_right_fork
                    synchronized(rightFork){
                        doAction(System.nanoTime() + ": Picked up right fork");
                        
                        GlobalClass.states[this.id] = 2;
                        //eat
                        doAction(System.nanoTime() + ": Eating");
                    }
                    
                    //put_down_right_fork
                    doAction(System.nanoTime() + ": Put down right fork");
                }
                
                //put_down_left_fork
                doAction(System.nanoTime() + ": Put down left fork");
                GlobalClass.states[this.id] = 0;
            }            
        } catch(InterruptedException e){
            System.out.println("Something went wrong... " + Thread.currentThread().getName() + " Interrupted");
            Thread.currentThread().interrupt();
            return;
        }
    }
    
}
