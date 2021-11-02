package com.mn210511.specialbonuscalculator;

import com.mn210511.specialbonuscalculator.services.Calculator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;


public class BonusCalculatorController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnAddFields;

    @FXML
    private CheckBox cbShiftYear;

    @FXML
    private ComboBox<Boolean> cmbWorkModell;

    @FXML
    private Label lblBonus;

    @FXML
    private TextField txtDays1;

    @FXML
    private TextField txtDays2;

    @FXML
    private TextField txtHours1;

    @FXML
    private TextField txtHours2;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    private Node[] hourFields = new Node[12];
    private Node[] dayFields = new Node[12];


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