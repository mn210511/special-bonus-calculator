package com.mn210511.specialbonuscalculator;

import com.mn210511.specialbonuscalculator.services.Calculator;
import com.mn210511.specialbonuscalculator.services.CommaFormatter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


public class BonusCalculatorController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button btnCopyBonus;
    @FXML
    private Button btnAddFields;

    @FXML
    private CheckBox cbShiftYear;

    @FXML
    private ComboBox<Double> cmbWorkModell;

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
    private Label lblAvg;
    @FXML
    private HBox hBox2;
    @FXML
    private GridPane mainGrid;
    @FXML
    private HBox hBox3;

    private Record;

    Calculator calculator;
    CommaFormatter fmt;
    private Node[] hourFields = new Node[12];
    private Node[] dayFields = new Node[12];

    public int entryCount = 0;

    public void initialize() {
        calculator = new Calculator();
        fmt = new CommaFormatter();

        // add all the predefined textfield to the array
        hourFields[entryCount] = txtHours1;
        dayFields[entryCount] = txtDays1;
        hourFields[++entryCount] = txtHours2;
        dayFields[entryCount] = txtDays2;

        cmbWorkModell.getItems().addAll(38.0, 38.5, 40.0);


    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onPlusButtonClick() {
        // a HBox to store the dynamic created textfield
        HBox tmp = new HBox();
        TextField txtHoursTmp = new TextField("0.0");
        hourFields[++entryCount] = txtHoursTmp;
        TextField txtDaysTmp = new TextField("0.0");
        dayFields[entryCount] = txtDaysTmp;
        tmp.getChildren().addAll(txtHoursTmp, txtDaysTmp);
        vBoxEntrys.getChildren().add(tmp);

        // count the rows. we do not want more than 12 rows of entrys
        if (entryCount == 11) {
            btnAddFields.setDisable(true);
        }


    }

    @FXML
    protected void onCalculateBonus() {
        List<Double> avgHourValues = new LinkedList<>();


        for (int i = 0; i <= entryCount; i++) {
            TextField t = (TextField) hourFields[i];
            System.out.println(t.getText());
            TextField t2 = (TextField) dayFields[i];
            System.out.println(t2.getText());
            System.out.println(cbShiftYear.selectedProperty().get());
            avgHourValues.add(calculator.averageHours(Double.parseDouble(t.getText()), Integer.parseInt(t2.getText()),
                    cbShiftYear.selectedProperty().get()));

        }

        double sumAvbHourValues = calculator.sumAverageHours(avgHourValues);
        double roundedAVG = Math.round(sumAvbHourValues * 1000.0) / 1000.0;

        double bonus = calculator.calculateBonus(Double.parseDouble(txtSalary.getText()), sumAvbHourValues,
                cmbWorkModell.getSelectionModel().getSelectedItem());

        lblAvg.setText(fmt.changeToComma(String.valueOf(roundedAVG)));
        double roundedBonus = Math.round(bonus * 1000.0) / 1000.0;

        lblBonus.setText(fmt.changeToComma(String.valueOf(roundedBonus)));

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        PDRectangle mediabox = page.getMediaBox();
        document.addPage(page);
        try {
            PDPageContentStream stream = new PDPageContentStream(document, page);
            stream.beginText();
            stream.setFont(PDType1Font.TIMES_ROMAN, 10);
           stream.newLineAtOffset(0, 25);

            stream.showText("TESt /n TEst");


            stream.endText();
            stream.moveTo(mediabox.getLowerLeftX(), mediabox.getLowerLeftY());
            stream.lineTo(mediabox.getUpperRightX(), mediabox.getUpperRightY());
            stream.stroke();
            stream.close();




        } catch (IOException e){
            System.err.println(e.getMessage());
        }


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save PDF");
        Window w = new PopupWindow() {
        };
        File file = fileChooser.showSaveDialog(w);
        try {
            document.save(file);
            document.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    protected void onCopyBonus() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();

        final ClipboardContent content = new ClipboardContent();
        content.putString(lblBonus.getText());
        clipboard.setContent(content);

    }


}