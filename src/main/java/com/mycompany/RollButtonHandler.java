package com.mycompany;

import java.util.ArrayList;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 *
 */
public class RollButtonHandler implements EventHandler<ActionEvent> {

    ArrayList<RotateTransition> rotates;
    Yahtzee yahtzee;
    ArrayList<Text> dTextArray;
    ArrayList<StackPane> dStackArray;

    public RollButtonHandler(Yahtzee yahtzee, ArrayList<Text> dTextArray, ArrayList<StackPane> dStackArray) {
        this.yahtzee = yahtzee;
        this.dTextArray = dTextArray;
        this.dStackArray = dStackArray;
    }

    @Override
    public void handle(ActionEvent event) {
        createRotateTransformationForDice();

        if (yahtzee.rolls < 3) {
            yahtzee.rollUnselectedDice();
            for (int i = 0; i < yahtzee.dice.length; i++) {
                if (!yahtzee.dice[i].isSelected) {
                    dTextArray.get(i).setText(String.format("%d", yahtzee.dice[i].getValue()));
                    rotates.get(i).play();
                }
            }
        } else {
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Three rolls maximum per turn! Please click Next Turn.");
            alert.show();
        }
        yahtzee.rolls += 1;
    }

    private void createRotateTransformationForDice() {
        rotates = new ArrayList<>();

        for (int i = 0; i < yahtzee.dice.length; i++) {
            var r = new RotateTransition(Duration.millis(100), dStackArray.get(i));
            rotates.add(r);
            rotates.get(i).setAxis(Rotate.Z_AXIS);
            rotates.get(i).setByAngle(360);
            rotates.get(i).setCycleCount(1);
        }
    }
}
