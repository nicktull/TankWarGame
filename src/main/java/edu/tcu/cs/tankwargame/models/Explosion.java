package edu.tcu.cs.tankwargame.models;

import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

/**
 * Represents an explosion animation in the Tank War Game.
 */
public class Explosion {
    private ImageView view;
    private int duration;

    /**
     * Constructs an Explosion object at a specific location within the game pane.
     * The explosion animation is displayed for a specified duration.
     *
     * @param position the position where the explosion will appear, given as a {@link Point2D} object
     * @param gamePane the game pane where the explosion will be displayed
     * @param duration the duration in milliseconds for which the explosion should be visible
     */
    public Explosion(Point2D position, Pane gamePane, int duration) {
        this.view = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7.gif"))));
        this.view.setX(position.getX());
        this.view.setY(position.getY());
        this.duration = duration;
        gamePane.getChildren().add(view);
        triggerAnimation(gamePane);
    }

    /**
     * Initiates a countdown for the explosion animation, removing the explosion from the pane upon completion.
     *
     * @param gamePane the pane from which the explosion will be removed after the animation duration
     */
    private void triggerAnimation(Pane gamePane) {
        PauseTransition pause = new PauseTransition(Duration.millis(duration));
        pause.setOnFinished(event -> gamePane.getChildren().remove(view));
        pause.play();
    }
}
