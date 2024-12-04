package edu.tcu.cs.tankwargame.app;

import edu.tcu.cs.tankwargame.controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class that launches the Tank War Game.
 * This class sets up the primary stage and loads the initial user interface from the FXML file.
 */
public class Main extends Application {

    /**
     * Starts the main stage for the application, loading the user interface and setting up the game environment.
     *
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception if there is an issue loading the FXML file or setting up the scene.
     */
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

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param args the command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
