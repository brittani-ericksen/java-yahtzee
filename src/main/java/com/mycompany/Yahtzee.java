package com.mycompany;

/**
 *
 * Yahtzee class
 * 
 */
public class Yahtzee {
    
    protected Die[] dice;
    protected int rolls;
    
    public Yahtzee() {
        dice = new Die[5];
        
        for(int i = 0; i <5; i++) {
            dice[i] = new Die();
        }
        
    }
    
    public void rollUnselectedDice() {
        for(Die d : dice) {
            if (!d.isSelected){
                d.setValue(d.roll());
            }
        }
    }
    
}
