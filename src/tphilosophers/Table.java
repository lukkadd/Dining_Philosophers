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
    final static int NUMBER_OF_SEATS = 5;
    final static int THINKING = 0;
    final static int HUNGRY = 1;
    final static int EATING = 2;
    private boolean[] forks = new boolean[NUMBER_OF_SEATS];
    public int[] states = new int[NUMBER_OF_SEATS];

    public synchronized void takeForks(int id) {
        states[id] = HUNGRY;
    }
    
    public synchronized void putForks(int id) {
    
    }
}
