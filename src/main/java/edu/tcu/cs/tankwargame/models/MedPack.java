package edu.tcu.cs.tankwargame.models;


import javafx.geometry.Point2D;

public class MedPack {
    protected Point2D position;
    //protected HealingPower healingPower;

    public MedPack() {
        this.position = new Point2D(0, 0);
    }
    public void applyHealing(){
        // Apply healing logic
    }

}
