package com.mycompany;

/**
 *
 * Die class 
 * 
 */

public class Die {

    protected int value;
    protected boolean isSelected;
    
    public Die() {
        isSelected = false;
    }
    
    public int roll() {
        // Generate random number from 1 to 6 to "roll" a die
        int randomNum = (int) Math.floor(Math.random() * (6 - 1 + 1) + 1);
        return randomNum;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public boolean getIsSelected() {
        return isSelected;
    }
    
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
}
