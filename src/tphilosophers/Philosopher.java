/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tphilosophers;

/**
 *
 * @author lukka
 */

public class Philosopher implements Runnable{
    
    final int MAX_TIME = 100;
    
    private int id;
    private Table table;
    
    
    public Philosopher(int id, Table table){
        this.id = id;
        this.table = table;
    }
    
    @Override 
    public void run(){
    
//        Philosopher Behaviour:

//      Run for eternity
        while(true){
            
            //think
            think((int)Math.random() * MAX_TIME);
            
            //take forks
            table.takeForks(this.id);
            
            //eat
            eat((int)Math.random() * MAX_TIME);
            
            //put down forks
            table.putForks(this.id);
        }

    }
    
    public void think(int time){
        try{
            Thread.sleep(time);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void eat(int time){
        try{
            Thread.sleep(time);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
