package com.mn210511.specialbonuscalculator;

import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class TestDocument {

    public static void main(String[] args) {
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

            float xStart = mediabox.getLowerLeftX();
            float yStart = mediabox.getLowerLeftY();

            float xEnd = mediabox.getUpperRightX();
            float yEnd = mediabox.getUpperRightY();

            int rows = 14;
            float gap = 20.0f;

            //drawing the horizontal lines
            for (int i = 0; i < rows; i++) {
                xStart = 100.0f;
                xEnd = xStart + 400.0f;
                yStart = 500.0f;
                yEnd = 500.0f;

                drawLine(stream, xStart, yStart - (gap * i), xEnd, yEnd - (gap * i));


            }

            //drawing the vertical lines
            int columns = 5;
            xEnd=xStart;
            yEnd= yStart-(rows*gap)+gap;
            gap = 100.0f;
            for (int i = 0; i < columns; i++){

                drawLine(stream, xStart+ (gap * i), yStart, xEnd+ (gap * i), yEnd);
            }


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
        stream.stroke();
    }
}
