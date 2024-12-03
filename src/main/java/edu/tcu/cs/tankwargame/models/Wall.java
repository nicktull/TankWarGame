package edu.tcu.cs.tankwargame.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wall {
    private ImageView view;

    public Wall(double x, double y, Pane gamePane) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/wall.jpg")));
        this.view = new ImageView(image);
        this.view.setX(x);
        this.view.setY(y);
        this.view.setFitWidth(50); // Set this to the actual width of your image
        this.view.setFitHeight(50); // Set this to the actual height of your image
        gamePane.getChildren().add(this.view);

    }

    // Static method to add walls directly into the gamePane with predefined locations
    public static List<Wall> setupWalls(Pane gamePane) {
        // Predefined wall locations
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(100, 50, gamePane));  // Top-left area
        walls.add(new Wall(700, 50, gamePane));  // Top-right area
        walls.add(new Wall(100, 500, gamePane)); // Bottom-left area
        walls.add(new Wall(700, 500, gamePane)); // Bottom-right area
        walls.add(new Wall(400, 300, gamePane)); // Center of the game area
        walls.add(new Wall(200, 150, gamePane)); // Mid-top left
        walls.add(new Wall(600, 150, gamePane)); // Mid-top right
        walls.add(new Wall(200, 450, gamePane)); // Mid-bottom left
        walls.add(new Wall(600, 450, gamePane)); // Mid-bottom right
        walls.add(new Wall(400, 100, gamePane)); // Near top-center
        walls.add(new Wall(400, 500, gamePane)); // Near bottom-center
        return walls;
    }

    public ImageView getView() {
        return view;
    }
}
