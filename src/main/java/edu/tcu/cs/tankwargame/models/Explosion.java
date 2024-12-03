package edu.tcu.cs.tankwargame.models;

import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class Explosion {
    private ImageView view;
    private int duration;

    public Explosion(Point2D position, Pane gamePane, int duration) {
        this.view = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7.gif"))));
        this.view.setX(position.getX());
        this.view.setY(position.getY());
        this.duration = duration;
        gamePane.getChildren().add(view);
        triggerAnimation(gamePane);
    }

    private void triggerAnimation(Pane gamePane) {
        PauseTransition pause = new PauseTransition(Duration.millis(duration));
        pause.setOnFinished(event -> gamePane.getChildren().remove(view));
        pause.play();
    }
}
