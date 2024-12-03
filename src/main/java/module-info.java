module edu.tcu.cs.tankwargame.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    // Export the controllers package to javafx.fxml
    opens edu.tcu.cs.tankwargame.controllers to javafx.fxml;

    // exports your packages where your FXML controllers are located
    exports edu.tcu.cs.tankwargame.app to javafx.graphics;
}
