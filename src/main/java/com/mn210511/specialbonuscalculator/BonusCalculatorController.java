package com.mn210511.specialbonuscalculator;

import com.mn210511.specialbonuscalculator.services.Calculator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;




public class BonusCalculatorController {
    @FXML
    private Label welcomeText;


    public void initialize() {
        Calculator calculator = new Calculator();


    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected  void onPlusButtonClick() {
        System.out.println("plus geklickt");
    }


}