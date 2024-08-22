module com.game.plankton_revenge {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.plankton_revenge to javafx.fxml;
    exports com.game.plankton_revenge;
}