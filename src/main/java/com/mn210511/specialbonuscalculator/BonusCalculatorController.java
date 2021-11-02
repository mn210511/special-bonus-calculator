package com.mn210511.specialbonuscalculator;

import com.mn210511.specialbonuscalculator.services.Calculator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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

    @FXML
    private VBox vBoxEntrys;
    @FXML
    private HBox hBox1;

    @FXML
    private HBox hBox2;
    @FXML
    private GridPane mainGrid;
    @FXML
    private HBox hBox3;

    private Node[] hourFields = new Node[12];
    private Node[] dayFields = new Node[12];

    public int entryCount = 0;

    public void initialize() {
        Calculator calculator = new Calculator();

        hourFields[entryCount] = txtHours1;
        dayFields[entryCount] = txtDays1;
        hourFields[++entryCount] = txtHours2;
        dayFields[entryCount] = txtDays2;

    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onPlusButtonClick() {

        HBox tmp = new HBox();
        TextField txtHoursTmp = new TextField("0.0");
        hourFields[++entryCount]=txtHoursTmp;
        TextField txtDaysTmp = new TextField("0.0");
        dayFields[entryCount]= txtDaysTmp;
        tmp.getChildren().addAll(txtHoursTmp, txtDaysTmp);
        vBoxEntrys.getChildren().add(tmp);
     if(entryCount==11){
         btnAddFields.setDisable(true);
     }

    }


}