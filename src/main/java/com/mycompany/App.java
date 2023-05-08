package com.mycompany;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

/**
 * Final Project
 * Author: Brittani Ericksen
 * Project Purpose: Playable Yahtzee game with the ability to save scores and load your previous scores.
 * Input: User keeps track of score for each line in Yahtzee scorecard. Buttons to roll dice, calculate total, save, and load scores.
 * Desired Output: The calculated score totals should be correct, and the previous scores saved should loan upon clicking the load button.
 * Variables and Classes: Main App class, Yahtzee and Die class, and two classes for die related click handlers.
 * Formulas: Adding the entered amount of points to get the score total.
 * Testing: When rolling, the die should rotate and randomly display a number. The user can select a die to prevent rolling it again, and reset dice for their nect turn.
 *          The user can enter numerical values only into the scorecard, and the total score should be properly calculated.
 *          When saved, the designated file should hold score data, and when load is clicked, this previously saved data should fill in the scorecard. 
 * April 24, 2023
 */
public class App extends Application {

    // Global fields
    Yahtzee yahtzee;
    ArrayList<StackPane> dStackArray;
    ArrayList<Text> dTextArray;
    ArrayList<TextField> allFields;
    StackPane dOneStack, dTwoStack, dThreeStack, dFourStack, dFiveStack;
    Text dOneText, dTwoText, dThreeText, dFourText, dFiveText;
    TextField aceInput, twosInput, threesInput, foursInput, fivesInput, sixesInput, threeOAKInput, fourOAKInput, fullHouseInput, smStraightInput, lgStraightInput, yahtzeeInput, chanceInput, yahtzeeBonusInput;
    TextField upperScoreTotal, upperBonusInput, upperTotal, lowerScoreTotal, grandTotal;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        yahtzee = new Yahtzee();
        
        var shear = new Shear();
        shear.setY(-.1);

        var yahtzeeHeader = new Text("YAHTZEE!");
        yahtzeeHeader.setTextAlignment(TextAlignment.CENTER);
        yahtzeeHeader.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.ITALIC,95));
        yahtzeeHeader.getTransforms().add(shear);
        var header = new StackPane(yahtzeeHeader);
        header.setPadding(new Insets(40, 10, 10, 10));
        
        var shadow = new DropShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);  
        shadow.setColor(Color.LIGHTGREEN); 
        shadow.setOffsetX(0);  
        shadow.setOffsetY(0);  
        shadow.setSpread(0.4);  
        shadow.setRadius(20);  
        
        // Create dice blocks
        dTextArray = new ArrayList<>();
        dStackArray = new ArrayList<>();
        
        var dOne = new Rectangle();
        dOne.setWidth(100);
        dOne.setHeight(100);
        dOne.setArcWidth(10);
        dOne.setArcHeight(10);
        dOne.setFill(Color.WHITE);
        dOne.setStroke(Color.BLACK);
        dOneText = new Text();
        dOneText.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,60));
        dOneStack = new StackPane(dOne, dOneText);
        dOneStack.setOnMouseClicked(event -> {
            if (!yahtzee.dice[0].isSelected) {
                yahtzee.dice[0].isSelected = true;
                dOneStack.setEffect(shadow);
            } else {
                yahtzee.dice[0].isSelected = false;
                dOneStack.setEffect(null);
            }
        });
        dTextArray.add(dOneText);
        dStackArray.add(dOneStack);

        var dTwo = new Rectangle();
        dTwo.setWidth(100);
        dTwo.setHeight(100);
        dTwo.setArcWidth(10);
        dTwo.setArcHeight(10);
        dTwo.setFill(Color.WHITE);
        dTwo.setStroke(Color.BLACK);
        dTwoText = new Text();
        dTwoText.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,60));
        dTwoStack = new StackPane(dTwo, dTwoText);
        dTwoStack.setOnMouseClicked(event -> {
            if (!yahtzee.dice[1].isSelected) {
                yahtzee.dice[1].isSelected = true;
                dTwoStack.setEffect(shadow);
            } else {
                yahtzee.dice[1].isSelected = false;
                dTwoStack.setEffect(null);
            }
        });
        dTextArray.add(dTwoText);
        dStackArray.add(dTwoStack);

        var dThree = new Rectangle();
        dThree.setWidth(100);
        dThree.setHeight(100);
        dThree.setArcWidth(10);
        dThree.setArcHeight(10);
        dThree.setFill(Color.WHITE);
        dThree.setStroke(Color.BLACK);
        dThreeText = new Text();
        dThreeText.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,60));
        dThreeStack = new StackPane(dThree, dThreeText);
        dThreeStack.setOnMouseClicked(event -> {
            if (!yahtzee.dice[2].isSelected) {
                yahtzee.dice[2].isSelected = true;
                dThreeStack.setEffect(shadow);
            } else {
                yahtzee.dice[2].isSelected = false;
                dThreeStack.setEffect(null);
            }
        });
        dTextArray.add(dThreeText);
        dStackArray.add(dThreeStack);

        var dFour = new Rectangle();
        dFour.setWidth(100);
        dFour.setHeight(100);
        dFour.setArcWidth(10);
        dFour.setArcHeight(10);
        dFour.setFill(Color.WHITE);
        dFour.setStroke(Color.BLACK);
        dFourText = new Text();
        dFourText.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,60));
        dFourStack = new StackPane(dFour, dFourText);
        dFourStack.setOnMouseClicked(event -> {
            if (!yahtzee.dice[3].isSelected) {
                yahtzee.dice[3].isSelected = true;
                dFourStack.setEffect(shadow);
            } else {
                yahtzee.dice[3].isSelected = false;
                dFourStack.setEffect(null);
            }
        });
        dTextArray.add(dFourText);
        dStackArray.add(dFourStack);

        var dFive = new Rectangle();
        dFive.setWidth(100);
        dFive.setHeight(100);
        dFive.setArcWidth(10);
        dFive.setArcHeight(10);
        dFive.setFill(Color.WHITE);
        dFive.setStroke(Color.BLACK);
        dFiveText = new Text();
        dFiveText.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,60));
        dFiveStack = new StackPane(dFive, dFiveText);
        dFiveStack.setOnMouseClicked(event -> {
            if (!yahtzee.dice[4].isSelected) {
                yahtzee.dice[4].isSelected = true;
                dFiveStack.setEffect(shadow);
            } else {
                yahtzee.dice[4].isSelected = false;
                dFiveStack.setEffect(null);
            }
        });
        dTextArray.add(dFiveText);
        dStackArray.add(dFiveStack);
        
        // Roll and next turn buttons
        
        var rollBtn = new Button("Roll!");
        rollBtn.setOnAction(new RollButtonHandler(yahtzee, dTextArray, dStackArray));
        var nextTurnBtn = new Button("Next turn");
        nextTurnBtn.setOnAction(new NewTurnButtonHandler(yahtzee, dTextArray, dStackArray));

        // Arrange dice
        
        var diceRowOne = new HBox(dOneStack, dTwoStack, dThreeStack);
        diceRowOne.setSpacing(20);
        diceRowOne.setAlignment(Pos.CENTER);
        var diceRowTwo = new HBox(dFourStack, dFiveStack);
        diceRowTwo.setSpacing(20);
        diceRowTwo.setAlignment(Pos.CENTER);
        var diceButtons = new HBox(rollBtn, nextTurnBtn);
        diceButtons.setSpacing(50);
        diceButtons.setAlignment(Pos.CENTER);

        var diceGrid = new VBox(diceRowOne, diceRowTwo, diceButtons);
        diceGrid.setSpacing(20);
        diceGrid.setPadding(new Insets(130, 10, 10, 50));

        
        // Create scorecard
        
        // Only accept numbers in textfields
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d*")) { 
                return change;
            }
            return null;
        };

        var upperLabel = new Label("UPPER SECTION");
        upperLabel.setStyle("-fx-font-weight: bold");
        var howToLabel = new Label("How to score");
        howToLabel.setStyle("-fx-font-weight: bold");

        var aceLabel = new Label("ACE");
        var aceRule = new Label("Count only aces");
        aceInput = new TextField();
        aceInput.setTextFormatter(new TextFormatter<String>(filter));
        var twosLabel = new Label("TWOS");
        var twosRule = new Label("Count only twos");
        twosInput = new TextField();
        twosInput.setTextFormatter(new TextFormatter<String>(filter));
        var threesLabel = new Label("THREES");
        var threesRule = new Label("Count only threes");
        threesInput = new TextField();
        threesInput.setTextFormatter(new TextFormatter<String>(filter));
        var foursLabel = new Label("FOURS");
        var foursRule = new Label("Count only fours");
        foursInput = new TextField();
        foursInput.setTextFormatter(new TextFormatter<String>(filter));
        var fivesLabel = new Label("FIVES");
        var fivesRule = new Label("Count only fives");
        fivesInput = new TextField();
        fivesInput.setTextFormatter(new TextFormatter<String>(filter));
        var sixesLabel = new Label("SIXES");
        var sixesRule = new Label("Count only sixes");
        sixesInput = new TextField();
        sixesInput.setTextFormatter(new TextFormatter<String>(filter));

        var upperScoreLabel = new Label("Upper Section Score");
        upperScoreTotal = new TextField();
        upperScoreTotal.setEditable(false);
        var upperBonusLabel = new Label("BONUS if over 63");
        var upperBonusRule = new Label("Score 35");
        upperBonusInput = new TextField();
        upperBonusInput.setEditable(false);

        var upperTotalLabel = new Label("Total Upper Score");
        upperTotal = new TextField("0");
        upperTotal.setEditable(false);

        var lowerLabel = new Label("LOWER SECTION");
        lowerLabel.setStyle("-fx-font-weight: bold");

        var threeOAKLabel = new Label("3 OF A KIND");
        var threeOAKRule = new Label("Add total of all dice");
        threeOAKInput = new TextField();
        threeOAKInput.setTextFormatter(new TextFormatter<String>(filter));
        var fourOAKLabel = new Label("4 OF A KIND");
        var fourOAKRule = new Label("Add total of all dice");
        fourOAKInput = new TextField();
        fourOAKInput.setTextFormatter(new TextFormatter<String>(filter));
        var fullHouseLabel = new Label("FULL HOUSE");
        var fullHouseRule = new Label("Score 25");
        fullHouseInput = new TextField();
        fullHouseInput.setTextFormatter(new TextFormatter<String>(filter));
        var smStraightLabel = new Label("SMALL STRAIGHT");
        var smStraightRule = new Label("Score 30");
        smStraightInput = new TextField();
        smStraightInput.setTextFormatter(new TextFormatter<String>(filter));
        var lgStraightLabel = new Label("LARGE STRAIGHT");
        var lgStraightRule = new Label("Score 40");
        lgStraightInput = new TextField();
        lgStraightInput.setTextFormatter(new TextFormatter<String>(filter));
        var yahtzeeLabel = new Label("YAHTZEE");
        var yahtzeeRule = new Label("Score 50");
        yahtzeeInput = new TextField();
        yahtzeeInput.setTextFormatter(new TextFormatter<String>(filter));
        var chanceLabel = new Label("CHANCE");
        var chanceRule = new Label("Score total of all dice");
        chanceInput = new TextField();
        chanceInput.setTextFormatter(new TextFormatter<String>(filter));
        var yahtzeeBonusLabel = new Label("YAHTZEE BONUS");
        var yahtzeeBonusRule = new Label("Score 100 for each bonus");
        yahtzeeBonusInput = new TextField();
        yahtzeeBonusInput.setTextFormatter(new TextFormatter<String>(filter));

        var lowerScoreLabel = new Label("Lower Section Score");
        lowerScoreTotal = new TextField("0");
        lowerScoreTotal.setEditable(false);

        var grandTotalLabel = new Label("GRAND TOTAL");
        grandTotalLabel.setStyle("-fx-font-weight: bold");
        var grandTotalBtn = new Button("Calculate");
        grandTotal = new TextField("0");
        grandTotal.setEditable(false);

        var scorecard = new GridPane();
        scorecard.setPadding(new Insets(10, 50, 10, 10));
        
        scorecard.addRow(0, upperLabel, howToLabel);
        
        scorecard.addRow(1, aceLabel, aceRule, aceInput);
        scorecard.addRow(2, twosLabel, twosRule, twosInput);
        scorecard.addRow(3, threesLabel, threesRule, threesInput);
        scorecard.addRow(4, foursLabel, foursRule, foursInput);
        scorecard.addRow(5, fivesLabel, fivesRule, fivesInput);
        scorecard.addRow(6, sixesLabel, sixesRule, sixesInput);
        scorecard.addRow(7, upperScoreLabel, new Label(""), upperScoreTotal);
        scorecard.addRow(8, upperBonusLabel, upperBonusRule, upperBonusInput);
        scorecard.addRow(9, upperTotalLabel, new Label(""), upperTotal);
        scorecard.addRow(10, new Label(""));
        
        scorecard.addRow(11, lowerLabel);
        scorecard.addRow(12, threeOAKLabel, threeOAKRule, threeOAKInput);
        scorecard.addRow(13, fourOAKLabel, fourOAKRule, fourOAKInput);
        scorecard.addRow(14, fullHouseLabel, fullHouseRule, fullHouseInput);
        scorecard.addRow(15, smStraightLabel, smStraightRule, smStraightInput);
        scorecard.addRow(16, lgStraightLabel, lgStraightRule, lgStraightInput);
        scorecard.addRow(17, yahtzeeLabel, yahtzeeRule, yahtzeeInput);
        scorecard.addRow(18, chanceLabel, chanceRule, chanceInput);
        scorecard.addRow(19, yahtzeeBonusLabel, yahtzeeBonusRule, yahtzeeBonusInput);
        scorecard.addRow(20, lowerScoreLabel, new Label(""), lowerScoreTotal);
        scorecard.addRow(21, grandTotalLabel, grandTotalBtn, grandTotal);

        // Scorecard buttons
        
        grandTotalBtn.setOnAction(new CalculateTotalButtonHandler());

        var saveBtn = new Button("Save Scores");
        var loadBtn = new Button("Load Previous Scores");
        saveBtn.setOnAction(new SaveButtonHandler());
        loadBtn.setOnAction(new LoadButtonHandler());

        var scoreButtons = new HBox(saveBtn, loadBtn);
        scoreButtons.setSpacing(50);
        scoreButtons.setAlignment(Pos.CENTER);

        var scores = new VBox(scorecard, scoreButtons);
        
        var root = new BorderPane();
        root.setTop(header);
        root.setLeft(diceGrid);
        root.setRight(scores);
        root.setStyle("-fx-background-color: #e1faf8");

        
        var scene = new Scene(root, 900, 800, Color.BLACK);
        stage.setTitle("YAHTZEE!");
        stage.setScene(scene);
        stage.show();
    }

    public void setInputArrayList() {
        allFields = new ArrayList<>();

        allFields.add(aceInput);
        allFields.add(twosInput);
        allFields.add(threesInput);
        allFields.add(foursInput);
        allFields.add(fivesInput);
        allFields.add(sixesInput);
        allFields.add(upperScoreTotal);
        allFields.add(upperBonusInput);
        allFields.add(upperTotal);
        allFields.add(threeOAKInput);
        allFields.add(fourOAKInput);
        allFields.add(fullHouseInput);
        allFields.add(smStraightInput);
        allFields.add(lgStraightInput);
        allFields.add(yahtzeeInput);
        allFields.add(chanceInput);
        allFields.add(yahtzeeBonusInput);
        allFields.add(lowerScoreTotal);
        allFields.add(grandTotal);
    }

    /*
    * Click Handlers
    */
    
    
    class CalculateTotalButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            setInputArrayList();
            calculateUpperScores();
            calculateLowerScores();
            int grandTotalScore = Integer.parseInt(upperTotal.getText()) + Integer.parseInt(lowerScoreTotal.getText());

            grandTotal.setText(String.format("%d", grandTotalScore));
        }

        public void calculateUpperScores() {
            int upperScore = 0;
            int totalUpperScore;

            for (int i = 0; i < 6; i++) {
                String input = allFields.get(i).getText();
                if (!input.isBlank()) {
                    upperScore += Integer.parseInt(input);
                }
            }

            if (upperScore >= 63) {
                totalUpperScore = upperScore + 35;
                upperBonusInput.setText("35");
            } else {
                totalUpperScore = upperScore;
                upperBonusInput.setText("0");
            }

            upperScoreTotal.setText(String.format("%d", upperScore));
            upperTotal.setText(String.format("%d", totalUpperScore));
        }

        public void calculateLowerScores() {
            int lowerScore = 0;

            for (int i = 9; i < 17; i++) {
                String input = allFields.get(i).getText();
                if (!input.isBlank()) {
                    lowerScore += Integer.parseInt(input);
                }
            }

            lowerScoreTotal.setText(String.format("%d", lowerScore));
        }
    }

    class SaveButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            try (java.io.FileWriter fileWriter = new FileWriter("resources/savedScores.txt")) {
                for (int i = 0; i < allFields.size(); i++) {
                    String input = allFields.get(i).getText();
                    if (!input.isBlank()) {
                        fileWriter.write(input + System.lineSeparator());
                    } else {
                        fileWriter.write("-1" + System.lineSeparator());
                    }
                }
                
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    class LoadButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try ( var s = new Scanner(new File("resources/savedScores.txt"))) {
                setInputArrayList();
                ArrayList<String> oldScores = new ArrayList<>();
                while (s.hasNext()) {
                    oldScores.add(s.nextLine());
                }
                
                for (int i = 0; i < oldScores.size(); i++) {
                    String output = oldScores.get(i);
                    if (Integer.parseInt(output) >= 0) {
                        allFields.get(i).setText(oldScores.get(i));
                    } else {
                        allFields.get(i).setText("");
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}
