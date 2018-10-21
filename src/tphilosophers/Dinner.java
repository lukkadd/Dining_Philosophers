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
public class Dinner extends processing.core.PApplet{
    private Table t;
    public void settings(){
        size(720,480);
    }
    
    public void setup(){
        t = new Table();
        for(int i = 0; i < 5; i++){
            t.forks[i] = true;
            Philosopher p = new Philosopher(i,t);
            Thread t = new Thread(p,"Philosopher " + (i+1));
            t.start();
        }
        frameRate(60);
    }
    
    public void draw(){
        background(51);
        ellipse(width/2,height/2,280,280);
        rect(mouseX,mouseY,10,10);
        for(int i = 0; i < t.NUMBER_OF_SEATS; i++){
            renderPhilosopher(i,t.states[i]);
            renderForks(i,t.forks[i]);
        }
    }
    
    private void renderPhilosopher(int philosopher, int state){
        int px = (width / 2) + (int) (200*Math.cos( (philosopher * 2 * Math.PI / 5) - Math.PI / 2));
        int py = (height / 2) + (int) (200*Math.sin( (philosopher * 2 * Math.PI / 5) - Math.PI / 2));
        pushStyle();
            switch(state){
                case 0:
                fill(100,100,100);
                break;
                case 1:
                fill(140,0,0);
                break;
                case 2:
                fill(0,140,0);
                break;
            }
            rect(px - 30,py-5,60,60,30,30,0,0);
            ellipse(px,py - 15,40,40);
        popStyle();
    }
    
    private void renderForks(int fork, boolean taken){
        int d1 = 100;
        int d2 = 50;
        if (!taken){
            d1 = 200;
            d2 = 150;
        }
        pushStyle();
        strokeWeight(3);
        line(
                width/2 + (int)(d2*Math.cos( (fork * 2 * Math.PI / 5) - 3*Math.PI / 4)),
                height/2 + (int)(d2*Math.sin( (fork * 2 * Math.PI / 5)- 3*Math.PI / 4)),
                width/2 + (int)(d1*Math.cos( (fork * 2 * Math.PI / 5)- 3*Math.PI / 4 )),
                height/2 + (int)(d1*Math.sin( (fork * 2 * Math.PI / 5)- 3*Math.PI / 4 ))
                );
        popStyle();
    }
}
