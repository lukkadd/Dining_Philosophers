/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tphilosophers;

import processing.core.*;

/**
 *
 * @author lukka
 */
public class Dinner {
    public static void main(String[] args) {
        Table t = new Table();
        for(int i = 0; i < 5; i++){
            new Philosopher(i,t).run();
        }
    }
}
