module com.mn210511.specialbonuscalculator {
    requires javafx.controls;
    requires  javafx.graphics;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    requires org.apache.pdfbox;

    opens com.mn210511.specialbonuscalculator to javafx.fxml;
    exports com.mn210511.specialbonuscalculator;
}