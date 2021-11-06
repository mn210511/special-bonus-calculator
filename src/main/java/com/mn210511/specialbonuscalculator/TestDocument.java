package com.mn210511.specialbonuscalculator;

import com.mn210511.specialbonuscalculator.entities.Record;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;

import java.io.File;
import java.io.IOException;

public class TestDocument {

   ;

    public static void main(String[] args) {
        Record testRecord = new Record("MyCompany", "Nicolas Herold", true, 2500.0);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        PDRectangle mediabox = page.getMediaBox();
        document.addPage(page);
        try {
            PDPageContentStream stream = new PDPageContentStream(document, page);


            float xStart = mediabox.getLowerLeftX();
            float yStart = mediabox.getLowerLeftY();

            float xEnd = mediabox.getUpperRightX();
            float yEnd = mediabox.getUpperRightY();

            int rows = 15;
            float gap = 20.0f;

            //drawing the horizontal lines
            for (int i = 0; i < rows; i++) {
                xStart = 100.0f;
                xEnd = xStart + 400.0f;
                yStart = 600.0f;
                yEnd = 600.0f;

                drawLine(stream, xStart, yStart - (gap * i), xEnd, yEnd - (gap * i));


            }
            stream.beginText();
            stream.setFont(PDType1Font.TIMES_ROMAN, 20);
            stream.newLineAtOffset(20, mediabox.getUpperRightY()-50);
            stream.setRenderingMode(RenderingMode.FILL_STROKE);
            stream.showText("Firma: ");
           // stream.newLineAtOffset(20, mediabox.getUpperRightY()-200);
            stream.newLineAtOffset(0, -20);
            stream.showText("Mitarbeiter: ");
           stream.setRenderingMode(RenderingMode.FILL);
            stream.setFont(PDType1Font.TIMES_ROMAN, 18);
            stream.newLineAtOffset(100, 0);
            stream.showText(testRecord.getEmployee());
            stream.newLineAtOffset(0, +20);
            stream.showText(testRecord.getCompany());
            stream.endText();


            //drawing the vertical lines
            int columns = 5;
            xEnd = xStart;
            yEnd = yStart - (rows * gap) + gap;
            gap = 100.0f;
            for (int i = 0; i < columns; i++) {

                drawLine(stream, xStart + (gap * i), yStart, xEnd + (gap * i), yEnd);
            }

            //longer lines on the outside border
            drawLine(stream, xStart, yStart, xStart, yEnd - 105);
            drawLine(stream, xStart + (gap * columns) - gap, yStart, xStart + (gap * columns) - gap, yEnd - 105);

            //line between summed AVG and the endresult
            drawLine(stream, xStart, yEnd-50, xEnd+(columns*gap)-gap, yEnd-50);

            //line for the bottom border
            drawLine(stream, xStart, yEnd-100, xEnd+(columns*gap)-gap, yEnd-100);
            drawLine(stream, xStart, yEnd-105, xEnd+(columns*gap)-gap, yEnd-105);


            System.out.println("xstart: " + xStart + "yStart" + yStart);
            System.out.println("xEnde: " + xEnd + "YEnde" + yEnd);

            stream.close();


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save PDF");

        File file = new File("/home/nicolas/Schreibtisch/test");
        try {
            document.save(file);
            document.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void drawLine(PDPageContentStream stream, float xStart, float yStart, float xEnd, float yEnd) throws IOException {
        stream.moveTo(xStart, yStart);
        stream.lineTo(xEnd, yEnd);
        stream.fillAndStroke();
    }

}
