module com.mn210511.specialbonuscalculator {
    requires javafx.controls;
    requires  javafx.graphics;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    requires org.apache.pdfbox;

    opens com.mn210511.specialbonuscalculator;
    exports com.mn210511.specialbonuscalculator;
}