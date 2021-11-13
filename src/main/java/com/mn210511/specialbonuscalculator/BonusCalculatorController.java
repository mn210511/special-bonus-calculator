package com.mn210511.specialbonuscalculator;

import com.mn210511.specialbonuscalculator.entities.RecordFullYearAverage;
import com.mn210511.specialbonuscalculator.entities.RecordThreeMonth;
import com.mn210511.specialbonuscalculator.entities.Worktime;
import com.mn210511.specialbonuscalculator.services.Calculator;
import com.mn210511.specialbonuscalculator.services.CommaFormatter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class BonusCalculatorController {


    @FXML
    private Label lblUZ;

    @FXML
    private Label lblWR;

    @FXML
    private Button btnAddFields;

    @FXML
    private Button btnCalc2;

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnCopyBonus;

    @FXML
    private Button btnExportPDF1;

    @FXML
    private Button btnExportPDF2;

    @FXML
    private CheckBox cbShiftYear;

    @FXML
    private CheckBox cbincSalary;

    @FXML
    private ComboBox<Double> cmbWorkModell;

    @FXML
    private DatePicker dtpBeginn1;

    @FXML
    private DatePicker dtpBeginn2;

    @FXML
    private DatePicker dtpEnd1;

    @FXML
    private DatePicker dtpEnd2;

    @FXML
    private HBox hBox1;

    @FXML
    private HBox hBox2;

    @FXML
    private HBox hBox3;

    @FXML
    private Label lbl3MAllowance;

    @FXML
    private Label lbl3MOvertime;

    @FXML
    private Label lbl3MSeg;


    @FXML
    private Label lbl3Msalary;

    @FXML
    private Label lblAvg;

    @FXML
    private Label lblBonus;

    @FXML
    private Label lblBonus3M;

    @FXML
    private GridPane mainGrid;

    @FXML
    private TextField txtAllowances1;

    @FXML
    private TextField txtAllowances2;

    @FXML
    private TextField txtAllowances3;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtCompany1;

    @FXML
    private TextField txtDays1;

    @FXML
    private TextField txtDays2;

    @FXML
    private TextField txtOvertime1;

    @FXML
    private TextField txtOvertime2;

    @FXML
    private TextField txtOvertime3;

    @FXML
    private TextField txtHours1;

    @FXML
    private TextField txtHours2;

    @FXML
    private TextField txtIncSalary;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtName1;

    @FXML
    private TextField txtSEG1;

    @FXML
    private TextField txtSEG2;

    @FXML
    private TextField txtSEG3;

    @FXML
    private TextField txtSal1;

    @FXML
    private TextField txtSal2;

    @FXML
    private TextField txtSal3;

    @FXML
    private TextField txtSalary;

    @FXML
    private VBox vBoxEntrys;
    @FXML
    private Button btnCopyChristmasBonus;

    @FXML
    private Button btnCopyHolidayAllowance;

    private RecordFullYearAverage record;
    private PDFCreator pdfCreator = new PDFCreator();

    Calculator calculator;
    CommaFormatter fmt;
    private Node[] hourFields = new Node[12];
    private Node[] dayFields = new Node[12];
    private Node[] beginDates = new Node[12];
    private Node[] endDates = new Node[12];

    public int entryCount = 0;

    public void initialize() {
        calculator = new Calculator();
        fmt = new CommaFormatter();
        txtIncSalary.setDisable(true);
        // add all the predefined textfield to the array
        hourFields[entryCount] = txtHours1;
        dayFields[entryCount] = txtDays1;
        beginDates[entryCount] = dtpBeginn1;
        endDates[entryCount] = dtpEnd1;
        hourFields[++entryCount] = txtHours2;
        dayFields[entryCount] = txtDays2;
        beginDates[entryCount] = dtpBeginn2;
        endDates[entryCount] = dtpEnd2;
        cbincSalary.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (txtIncSalary.disableProperty().get() == true) {
                    txtIncSalary.setDisable(false);
                } else {
                    txtIncSalary.setDisable(true);
                }
            }
        });

        cmbWorkModell.getItems().addAll(38.0, 38.5, 40.0);


    }


    @FXML
    protected void onPlusButtonClick() {
        // a HBox to store the dynamic created textfield
        HBox tmp = new HBox();
        DatePicker beginTmp = new DatePicker();
        DatePicker endTMP = new DatePicker();
        TextField txtHoursTmp = new TextField("0.0");
        hourFields[++entryCount] = txtHoursTmp;
        TextField txtDaysTmp = new TextField("0.0");
        dayFields[entryCount] = txtDaysTmp;
        tmp.getChildren().addAll(txtHoursTmp, txtDaysTmp, beginTmp, endTMP);
        endDates[entryCount] = endTMP;
        beginDates[entryCount] = beginTmp;
        vBoxEntrys.getChildren().add(tmp);

        // count the rows. we do not want more than 12 rows of entrys
        if (entryCount == 11) {
            btnAddFields.setDisable(true);
        }


    }

    @FXML
    protected void onCalculateBonus() {
        double salary;

        try {
            salary = Double.parseDouble(txtSalary.getText());
        } catch (NumberFormatException e) {
            salary = Double.parseDouble(fmt.changeToDot(txtSalary.getText()));
        }


        record = new RecordFullYearAverage(txtCompany.getText(), txtName.getText(), cbShiftYear.isSelected(), salary);

        List<Double> avgHourValues = new LinkedList<>();
        List<Worktime> worktimes = new LinkedList<>();

        for (int i = 0; i <= entryCount; i++) {
            TextField t = (TextField) hourFields[i];
            DatePicker p = (DatePicker) beginDates[i];
            DatePicker pEnd = (DatePicker) endDates[i];
            System.out.println(t.getText());
            TextField t2 = (TextField) dayFields[i];
            System.out.println(t2.getText());
            System.out.println(cbShiftYear.selectedProperty().get());
            double hours;
            try {
                hours = Double.parseDouble(t.getText());
            } catch (NumberFormatException e) {
                hours = Double.parseDouble(fmt.changeToDot(t.getText()));
            }


            Worktime tmp = new Worktime(hours, Integer.parseInt(t2.getText()), p.getValue(), pEnd.getValue());
            tmp.setAverage(calculator.averageHours(tmp.getHoursPerWeek(), tmp.getDuration(), record.isShiftyear()));
            worktimes.add(tmp);
            avgHourValues.add(tmp.getAverage());

        }
        record.setWorktimes(worktimes);
        double summedAvgHourValues = calculator.sumAverageHours(avgHourValues);
        record.setAverage(calculator.round(summedAvgHourValues));


        double holidayAllowance = calculator.round(calculator.calculateBonus(record.getSalary(), summedAvgHourValues,
                cmbWorkModell.getSelectionModel().getSelectedItem()));
        if (cbincSalary.isSelected() == true) {
            record.setSalary(Double.parseDouble(txtIncSalary.getText()));
        }
        double christmasAllowance = calculator.round(calculator.calculateBonus(record.getSalary(), summedAvgHourValues,
                cmbWorkModell.getSelectionModel().getSelectedItem()));

        record.setBonusUB(holidayAllowance);
        record.setBonusWR(christmasAllowance);
        record.setBonusTotal(holidayAllowance + christmasAllowance);

        lblUZ.setText(fmt.changeToComma(String.valueOf(holidayAllowance)));
        lblWR.setText(fmt.changeToComma(String.valueOf(christmasAllowance)));


        lblAvg.setText(fmt.changeToComma(String.valueOf(record.getAverage())));


        lblBonus.setText(fmt.changeToComma(String.valueOf(record.getBonusTotal())));



    }

    @FXML
    protected void onExportPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save PDF");
        Window w = new PopupWindow() {
        };
        File file = fileChooser.showSaveDialog(w);
        try {
            pdfCreator.exportFullYearCalculation(record, file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    protected void onCalculateThreeMonthAverage() {
        RecordThreeMonth record = new RecordThreeMonth(txtCompany1.getText(), txtName1.getText());
        double[] divAllowances = new double[3];
        double[] overtimes = new double[3];
        double[] dirtAllowances = new double[3];
        double[] salarys = new double[3];

        divAllowances[0] = safeParsedDouble(txtAllowances1.getText());
        divAllowances[1] = safeParsedDouble(txtAllowances2.getText());
        divAllowances[2] = safeParsedDouble(txtAllowances3.getText());

        overtimes[0] = safeParsedDouble(txtOvertime1.getText());
        overtimes[1] = safeParsedDouble(txtOvertime2.getText());
        overtimes[2] = safeParsedDouble(txtOvertime3.getText());

        dirtAllowances[0] = safeParsedDouble(txtSEG1.getText());
        dirtAllowances[1] = safeParsedDouble(txtSEG2.getText());
        dirtAllowances[2] = safeParsedDouble(txtSEG3.getText());

        salarys[0] = safeParsedDouble(txtSal1.getText());
        salarys[1] = safeParsedDouble(txtSal2.getText());
        salarys[2] = safeParsedDouble(txtSal3.getText());

        record.setOvertimes(overtimes);
        record.setSalarys(salarys);
        record.setDirtAllowances(dirtAllowances);
        record.setDivAllowances(divAllowances);

        record.setAvgOvertime(calculator.calculateThreeMonthAverage(overtimes));
        record.setAvgDirtAllowance(calculator.calculateThreeMonthAverage(dirtAllowances));
        record.setAvgSalary(calculator.calculateThreeMonthAverage(salarys));
        record.setAvgDivAllowance(calculator.calculateThreeMonthAverage(divAllowances));

        lbl3MAllowance.setText(fmt.changeToComma(String.valueOf(calculator.round(record.getAvgDivAllowance()))));
        lbl3MOvertime.setText(fmt.changeToComma(String.valueOf(calculator.round(record.getAvgOvertime()))));
        lbl3Msalary.setText(fmt.changeToComma(String.valueOf(calculator.round(record.getAvgSalary()))));
        lbl3MSeg.setText(fmt.changeToComma(String.valueOf(calculator.round(record.getAvgDirtAllowance()))));

        record.setBonus(calculator.sum(record.getAvgDivAllowance(), record.getAvgOvertime(), record.getAvgSalary(),
                record.getAvgDirtAllowance()));
        lblBonus3M.setText(fmt.changeToComma(String.valueOf(record.getBonus())));

    }


    @FXML
    protected void onCopyBonus(ActionEvent ev) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        Button tmp = (Button) ev.getSource();
        final ClipboardContent content = new ClipboardContent();
        if (tmp.getId().equals(btnCopyBonus.getId())) {
            content.putString(lblBonus.getText());
        } else if (tmp.getId().equals(btnCopyChristmasBonus.getId())) {
            content.putString(lblWR.getText());
        } else {
            content.putString(lblUZ.getText());
        }
        clipboard.setContent(content);

    }

    private double safeParsedDouble(String txt) {
        double ret;
        try {
            ret = Double.parseDouble(txt);
        } catch (NumberFormatException e) {
            ret = Double.parseDouble(fmt.changeToDot(txt));
        }


        return ret;
    }

}