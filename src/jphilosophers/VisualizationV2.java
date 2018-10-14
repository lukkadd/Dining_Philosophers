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
public class VisualizationV2 extends processing.core.PApplet{
    
    PhilosopherV1[] philosophers;
    
    public void settings(){
        size(720,480);
    }
    
    public void setup(){
        
        //initializing philosopher and fork arrays
        philosophers = new PhilosopherV1[5];
        Object[] forks = new Object[5];
        
        //initializing Fork objects
        for(int i = 0; i < forks.length; i++){
            forks[i] = new Object();
        }
        
        //initializing Philosopher objects
        for(int i = 0; i < philosophers.length; i++){
            philosophers[i] = new PhilosopherV1(
                    forks[i],
                    forks[(i+1) % forks.length],
                    (width / 2) + (int) (200*Math.cos( (i * 2 * Math.PI / 5) - Math.PI / 2)),
                    (height / 2) + (int) (200*Math.sin( (i * 2 * Math.PI / 5) - Math.PI / 2))
            );
            
            //creating the philosopher thread and running it
            Thread t = new Thread(philosophers[i],"Philosopher " + (i+1));
            t.start();
        }
        
        frameRate(60);
    }
    
    public void draw(){
        background(51);
        ellipse(width/2,height/2,280,280);
        rect(mouseX,mouseY,10,10);
        for(int i = 0; i < philosophers.length; i++){
            renderPhilosopher(philosophers[i]);
        }
    }
    
    private void renderPhilosopher(PhilosopherV1 p){
        pushStyle();
            switch(p.state){
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
            rect(p.posx - 30,p.posy-5,60,60,30,30,0,0);
            ellipse(p.posx,p.posy - 15,40,40);
        popStyle();
    }
    
    private void renderFork(int index){
        
    }
}
