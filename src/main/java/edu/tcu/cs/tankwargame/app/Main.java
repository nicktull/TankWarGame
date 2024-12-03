package edu.tcu.cs.tankwargame.app;

import edu.tcu.cs.tankwargame.controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main FXML file for the game interface
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/tcu/cs/tankwargame/game.fxml"));
        Parent root = fxmlLoader.load();

        // Set the title of the window
        primaryStage.setTitle("Tank War Game");

        // Create and set the scene
        Scene scene = new Scene(root, 870, 760); // Adjust the size of the window
        String css = this.getClass().getResource("/styles/main.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);

        //Optionally, set a minimum window size
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);

        //Show the primary stage
        primaryStage.show();

        GameController controller = fxmlLoader.getController();
        controller.setupEventHandlers(scene);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
