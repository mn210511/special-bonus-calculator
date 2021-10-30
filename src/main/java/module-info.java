module com.mn210511.specialbonuscalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.mn210511.specialbonuscalculator to javafx.fxml;
    exports com.mn210511.specialbonuscalculator;
}