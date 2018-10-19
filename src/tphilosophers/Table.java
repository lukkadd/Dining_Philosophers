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
public class Table {
    final public static int NUMBER_OF_SEATS = 5;
    final static int THINKING = 0;
    final static int HUNGRY = 1;
    final static int EATING = 2;
    private boolean[] forks = new boolean[NUMBER_OF_SEATS];
    public int[] states = new int[NUMBER_OF_SEATS];

    //Only one philosopher can use takeForks at a time
    public synchronized void takeForks(int id) {
        states[id] = HUNGRY;
        while( states [(id - 1 + NUMBER_OF_SEATS)%NUMBER_OF_SEATS] == EATING || //checks if the left philosopher is eating
               states [(id + 1)%NUMBER_OF_SEATS] == EATING ){ //or if the right philosopher is eating
            try{
                wait();
            }catch(InterruptedException e){
            }
        }
            forks[(id - 1 + NUMBER_OF_SEATS)%NUMBER_OF_SEATS] = false; //takes left fork
            forks[(id + 1)%NUMBER_OF_SEATS] = false; //takes right fork
            //When you have both  forks, eat
            states[id] = EATING;
            
    }
    
    public synchronized void putForks(int id) {
        forks[(id - 1 + NUMBER_OF_SEATS)%NUMBER_OF_SEATS] = true; //put down left fork
        forks[(id + 1)%NUMBER_OF_SEATS] = true; //put down right fork
        //if a philosopher on the left or the right is hungry, notify all threads that they can try to take the forks
        if( states [(id - 1 + NUMBER_OF_SEATS)%NUMBER_OF_SEATS] == HUNGRY ||
            states [(id + 1)%NUMBER_OF_SEATS] == HUNGRY ){
            notifyAll();
        }
        states[id] = THINKING;
        
    }
}
