package com.mycompany;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 *
 */
public class NewTurnButtonHandler implements EventHandler<ActionEvent> {
    
    Yahtzee yahtzee;
    ArrayList<Text> dTextArray;
    ArrayList<StackPane> dStackArray;
    
    public NewTurnButtonHandler(Yahtzee yahtzee, ArrayList<Text> dTextArray, ArrayList<StackPane> dStackArray) {
        this.yahtzee = yahtzee;
        this.dTextArray = dTextArray;
        this.dStackArray = dStackArray;
    }

    @Override
    public void handle(ActionEvent event) {
        // Reset dice and rolls count
        for (int i = 0; i < yahtzee.dice.length; i++) {
            dTextArray.get(i).setText("");
            dStackArray.get(i).setEffect(null);
        }

        for (Die d : yahtzee.dice) {
            if (d.isSelected) {
                d.isSelected = false;
            }
        }

        yahtzee.rolls = 0;
    }
}
