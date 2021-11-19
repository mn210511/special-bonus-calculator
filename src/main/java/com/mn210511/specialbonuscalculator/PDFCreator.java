package com.mn210511.specialbonuscalculator;

import com.mn210511.specialbonuscalculator.entities.Record;
import com.mn210511.specialbonuscalculator.entities.RecordFullYearAverage;
import com.mn210511.specialbonuscalculator.entities.RecordThreeMonth;
import com.mn210511.specialbonuscalculator.entities.Worktime;
import com.mn210511.specialbonuscalculator.services.Calculator;
import com.mn210511.specialbonuscalculator.services.CommaFormatter;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDFCreator {

    CommaFormatter commaFormatter = new CommaFormatter();
    Calculator calc = new Calculator();

    public static void main(String[] args) {
        PDFCreator test = new PDFCreator();
        RecordFullYearAverage testRecord = new RecordFullYearAverage("MyCompany", "Nicolas Herold", true, 2500.0);
        List<Worktime> worktimes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Worktime tmp = new Worktime(20.0 + i, 10, LocalDate.now(), LocalDate.now());
            tmp.setAverage(2000);
            worktimes.add(tmp);


        }
        testRecord.setWorktimes(worktimes);

        RecordThreeMonth rec = new RecordThreeMonth("Company", "nicolas");
        double[] tmp = {3000.00, 1000.00, 2222.0};

        rec.setSalarys(tmp);
        rec.setOvertimes(tmp);
        rec.setDivAllowances(tmp);
        rec.setDirtAllowances(tmp);

        rec.setBonus(15500);
        rec.setAvgSalary(546);
        rec.setAvgDivAllowance(45);
        rec.setAvgOvertime(486);
        rec.setAvgDirtAllowance(546);



        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save PDF");

        File file = new File("/home/nicolas/Schreibtisch/test");

        try {
            test.expoort3MonthAverageCalculation(rec, file);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

    private static void drawLine(PDPageContentStream stream, float xStart, float yStart, float xEnd, float yEnd) throws IOException {
        stream.moveTo(xStart, yStart);
        stream.lineTo(xEnd, yEnd);
        stream.fillAndStroke();
    }

    private void exportFullYearCalculation(RecordFullYearAverage record, File file) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        PDRectangle mediabox = page.getMediaBox();
        document.addPage(page);

        PDPageContentStream stream = new PDPageContentStream(document, page);


        float xStart = mediabox.getLowerLeftX();
        float yStart = mediabox.getLowerLeftY();

        float xEnd = mediabox.getUpperRightX();
        float yEnd = mediabox.getUpperRightY();

        int rows = 15;
        float gap = 20.0f;

        //drawing the horizontal lines
        for (int i = 0; i < rows; i++) {

            xStart = 70.0f;
            xEnd = xStart + 430.0f;
            yStart = 600.0f;
            yEnd = 600.0f;

            drawLine(stream, xStart, yStart - (gap * i), xEnd, yEnd - (gap * i));


        }
        stream.beginText();
        stream.setFont(PDType1Font.TIMES_ROMAN, 20);
        stream.newLineAtOffset(20, mediabox.getUpperRightY() - 50);
        stream.setRenderingMode(RenderingMode.FILL_STROKE);
        stream.showText("Firma: ");

        stream.newLineAtOffset(0, -20);
        stream.showText("Mitarbeiter: ");
        stream.setRenderingMode(RenderingMode.FILL);
        stream.setFont(PDType1Font.TIMES_ROMAN, 18);
        stream.newLineAtOffset(100, 0);
        stream.showText(record.getEmployee());
        stream.newLineAtOffset(0, +20);
        stream.showText(record.getCompany());
        stream.endText();


        stream.beginText();
        stream.setFont(PDType1Font.TIMES_ROMAN, 20);
        stream.newLineAtOffset(20, mediabox.getUpperRightY() - 100);
        stream.setRenderingMode(RenderingMode.FILL_STROKE);
        stream.showText("Gehalt: ");
        stream.newLineAtOffset(150, 0);
        stream.setRenderingMode(RenderingMode.FILL);
        stream.showText(commaFormatter.changeToComma(String.valueOf(record.getSalary())));

        if (record.isIncreasedSalary()) {
            stream.setRenderingMode(RenderingMode.FILL_STROKE);
            stream.newLineAtOffset(-150, -20);
            stream.showText("Gehalt (neu): ");
            stream.setRenderingMode(RenderingMode.FILL);
            stream.setFont(PDType1Font.TIMES_ROMAN, 18);

            stream.newLineAtOffset(150, 0);
            stream.showText(commaFormatter.changeToComma(String.valueOf(record.getNewSalary())));
        }
        stream.endText();



        float lineOffset = 210.0f;

        //Headlines
        stream.beginText();
        stream.newLineAtOffset(75, mediabox.getUpperRightY() - lineOffset);
        stream.setFont(PDType1Font.COURIER, 15);
        stream.setRenderingMode(RenderingMode.FILL_STROKE);
        stream.showText("Datum");
        stream.newLineAtOffset(107.3f, 0);
        stream.showText("Stunden");
        stream.newLineAtOffset(107.3f, 0);
        stream.showText("Tage");
        stream.newLineAtOffset(107.3f, 0);
        stream.showText("Schnitt");
        stream.endText();
        lineOffset = 230.0f;

        // values of the Calculation inside the cells
        String withoutYear = "dd.MM";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(withoutYear);
        for (Worktime w : record.getWorktimes()) {
            stream.setFont(PDType1Font.COURIER, 12);
            stream.setRenderingMode(RenderingMode.FILL);
            stream.beginText();
            stream.newLineAtOffset(75, mediabox.getUpperRightY() - lineOffset);
            stream.showText(fmt.format(w.getBegin()) + " - " + fmt.format(w.getEnd()));
            stream.newLineAtOffset(107.3f, 0);
            stream.showText(commaFormatter.changeToComma(String.valueOf(w.getHoursPerWeek())));
            stream.newLineAtOffset(107.3f, 0);
            stream.showText((String.valueOf(w.getDuration())));
            stream.newLineAtOffset(107.3f, 0);
            double roundedAvg = calc.round(w.getAverage());
            stream.showText(commaFormatter.changeToComma(String.valueOf(roundedAvg)));
            stream.endText();
            lineOffset -= -20;
        }

        //Bottom line of the table
        stream.beginText();
        stream.newLineAtOffset(290, mediabox.getUpperRightY() - lineOffset);
        stream.showText(String.valueOf(record.getSummedDays()));
        stream.newLineAtOffset(107.3f, 0);
        stream.showText(commaFormatter.changeToComma(String.valueOf(record.getAverage())));

        stream.endText();


        //Line for the average
        stream.beginText();
        stream.setRenderingMode(RenderingMode.FILL_STROKE);
        stream.newLineAtOffset(80, mediabox.getUpperRightY() - 520);
        stream.showText("Schnitt:");
        stream.newLineAtOffset(320, 0);
        stream.showText(commaFormatter.changeToComma(String.valueOf(record.getAverage())));
        stream.endText();

        //line for the endresult

        stream.beginText();
        stream.newLineAtOffset(80, mediabox.getUpperRightY() - 570);
        stream.showText("Sonderzahlung gesamt:");
        stream.newLineAtOffset(320, 0);
        stream.showText(commaFormatter.changeToComma(String.valueOf(record.getBonusTotal())));
        stream.endText();

        //drawing the vertical lines
        int columns = 5;
        xEnd = xStart;
        yEnd = yStart - (rows * gap) + gap;
        gap = 107.3f;
        for (int i = 0; i < columns; i++) {

            drawLine(stream, xStart + (gap * i), yStart, xEnd + (gap * i), yEnd);
        }

        //longer lines on the outside border
        drawLine(stream, xStart, yStart, xStart, yEnd - 105);
        drawLine(stream, xStart + (gap * columns) - gap, yStart, xStart + (gap * columns) - gap, yEnd - 105);

        //line between summed AVG and the endresult
        drawLine(stream, xStart, yEnd - 50, xEnd + (columns * gap) - gap, yEnd - 50);

        //line for the bottom border
        drawLine(stream, xStart, yEnd - 100, xEnd + (columns * gap) - gap, yEnd - 100);
        drawLine(stream, xStart, yEnd - 105, xEnd + (columns * gap) - gap, yEnd - 105);

// Line with the creation Date at the bottom
        stream.beginText();
        stream.setRenderingMode(RenderingMode.FILL);
        stream.setFont(PDType1Font.TIMES_ROMAN, 15);
        stream.newLineAtOffset(mediabox.getUpperRightX() - 150, mediabox.getLowerLeftY() + 20);

        stream.showText("Erstellt am " + DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.now()));
        stream.endText();


        System.out.println("xstart: " + xStart + "yStart" + yStart);
        System.out.println("xEnde: " + xEnd + "YEnde" + yEnd);

        stream.beginText();
        stream.setFont(PDType1Font.COURIER, 15);
        stream.newLineAtOffset(75, 175);
        stream.showText("Weihnachtsgeld: " + record.getBonusWR() + " €");
        stream.newLineAtOffset(0, -25);
        stream.showText("Urlaubsgeld: " + record.getBonusUB() + " €");
        stream.endText();

        stream.close();

        document.save(file);
        document.close();

    }

    private void expoort3MonthAverageCalculation(RecordThreeMonth record, File file) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        PDRectangle mediabox = page.getMediaBox();
        document.addPage(page);

        PDPageContentStream stream = new PDPageContentStream(document, page);


        float xStart = mediabox.getLowerLeftX();
        float yStart = mediabox.getLowerLeftY();

        float xEnd = mediabox.getUpperRightX();
        float yEnd = mediabox.getUpperRightY();

        int rows = 5;
        float gap = 20.0f;

        //drawing the horizontal lines
        for (int i = 0; i < rows; i++) {

            xStart = 45.0f;
            xEnd = xStart + 107.3f*5;
            yStart = 550.0f;
            yEnd = 550.0f;

            drawLine(stream, xStart, yStart - (gap * i), xEnd, yEnd - (gap * i));


        }
        stream.beginText();
        stream.setFont(PDType1Font.TIMES_ROMAN, 20);
        stream.newLineAtOffset(20, mediabox.getUpperRightY() - 50);
        stream.setRenderingMode(RenderingMode.FILL_STROKE);
        stream.showText("Firma: ");

        stream.newLineAtOffset(0, -20);
        stream.showText("Mitarbeiter: ");
        stream.setRenderingMode(RenderingMode.FILL);
        stream.setFont(PDType1Font.TIMES_ROMAN, 18);
        stream.newLineAtOffset(100, 0);
        stream.showText(record.getEmployee());
        stream.newLineAtOffset(0, +20);
        stream.showText(record.getCompany());
        stream.endText();


        float lineOffset = 230.0f;

        stream.beginText();
        stream.newLineAtOffset(50, mediabox.getUpperRightY()-lineOffset);
        stream.setFont(PDType1Font.COURIER, 15);
        stream.showText("3 Monatsschnitt vor Fälligkeit:");
        stream.endText();


        lineOffset = 260.0f;
        //Headlines

        double[] tmp = record.getSalarys();

        fillValues(mediabox.getUpperRightY() - lineOffset, stream, tmp, "Gehalt:", record.getAvgSalary());
        lineOffset = 280.0f;

        // values of the Calculation inside the cells

   tmp = record.getOvertimes();
        fillValues(mediabox.getUpperRightY() - lineOffset, stream, tmp, "Mehrstunden:", record.getAvgOvertime());

        lineOffset -= -20;
        tmp = record.getDirtAllowances();

        fillValues(mediabox.getUpperRightY() - lineOffset, stream, tmp, "SEG:", record.getAvgDirtAllowance());

        lineOffset -= -20;
        tmp = record.getDivAllowances();

        fillValues(mediabox.getUpperRightY() - lineOffset, stream, tmp, "Zulagen:", record.getAvgDivAllowance());

        lineOffset -= -20;



        stream.beginText();
        stream.setFont(PDType1Font.COURIER, 15);
        stream.newLineAtOffset(50, mediabox.getUpperRightY() - 415);
        stream.setRenderingMode(RenderingMode.FILL_STROKE);
        stream.showText("Sonderzahlung gesamt:");
        stream.newLineAtOffset(107.3f*4, 0);
        stream.showText(commaFormatter.changeToComma(String.valueOf(calc.round(record.getBonus()))));
        stream.endText();



        //drawing the vertical lines
        int columns = 6;
        xEnd = xStart;
        yEnd = yStart - (rows * gap) + gap;
        gap = 107.3f;
        for (int i = 0; i < columns; i++) {

            drawLine(stream, xStart + (gap * i), yStart, xEnd + (gap * i), yEnd);
        }

        //longer lines on the outside border
        drawLine(stream, xStart, yStart, xStart, yEnd - 105);
        drawLine(stream, xStart + (gap * columns) - gap, yStart, xStart + (gap * columns) - gap, yEnd - 105);

        //line between summed AVG and the endresult
        drawLine(stream, xStart, yEnd - 50, xEnd + (columns * gap) - gap, yEnd - 50);

        //line for the bottom border
        drawLine(stream, xStart, yEnd - 100, xEnd + (columns * gap) - gap, yEnd - 100);
        drawLine(stream, xStart, yEnd - 105, xEnd + (columns * gap) - gap, yEnd - 105);

// Line with the creation Date at the bottom
        stream.beginText();
        stream.setRenderingMode(RenderingMode.FILL);
        stream.setFont(PDType1Font.TIMES_ROMAN, 15);
        stream.newLineAtOffset(mediabox.getUpperRightX() - 150, mediabox.getLowerLeftY() + 20);

        stream.showText("Erstellt am " + DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.now()));
        stream.endText();


        System.out.println("xstart: " + xStart + "yStart" + yStart);
        System.out.println("xEnde: " + xEnd + "YEnde" + yEnd);



        stream.close();

        document.save(file);
        document.close();

    }

    private void fillValues(float lineOffset, PDPageContentStream stream, double[] tmp, String headline, double avgValue) throws IOException {
        stream.setFont(PDType1Font.COURIER, 12);
        stream.setRenderingMode(RenderingMode.FILL);
        stream.beginText();
        stream.newLineAtOffset(50, lineOffset);
        stream.showText(headline);
        stream.newLineAtOffset(107.3f, 0);
        stream.showText(commaFormatter.changeToComma(String.valueOf(calc.round(tmp[0]))));
        stream.newLineAtOffset(107.3f, 0);
        stream.showText(commaFormatter.changeToComma(String.valueOf(calc.round(tmp[1]))));
        stream.newLineAtOffset(107.3f, 0);

        stream.showText(commaFormatter.changeToComma(String.valueOf(calc.round(tmp[2]))));
        stream.newLineAtOffset(107.3f, 0);
        stream.showText(commaFormatter.changeToComma(String.valueOf(calc.round(avgValue))));
        stream.endText();
    }


    public void exportPDF(Record record, File file) throws IOException {

        if (record instanceof RecordFullYearAverage) {
            exportFullYearCalculation((RecordFullYearAverage) record, file);
        } else if (record instanceof RecordThreeMonth) {
            expoort3MonthAverageCalculation((RecordThreeMonth) record, file);
        }


    }
}
