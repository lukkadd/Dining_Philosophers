/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tphilosophers;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukka
 */

public class Philosopher implements Runnable{
    
    final int MAX_TIME = 2000;
    
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
            
            try {
                //think
                think((long) (Math.random() * MAX_TIME));
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //take forks
            table.takeForks(this.id);
            
            try {
                //eat
                eat((long) (Math.random() * MAX_TIME));
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //put down forks
            table.putForks(this.id);
        }

    }
    
    public void think(long time)throws InterruptedException{
            Thread.sleep(time);
    }
    
    public void eat(long time) throws InterruptedException{
        Thread.sleep(time);
    }
}
