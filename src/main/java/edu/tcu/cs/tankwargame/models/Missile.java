package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents a missile in the Tank War Game.
 * This class manages missile properties such as position, direction, speed, and graphical representation.
 */
public class Missile {
    private Point2D position;
    private Direction direction;
    private final double speed;
    private String owner; // "player" or "enemy"
    private ImageView sprite;

    /**
     * Constructs a Missile object with specified coordinates, direction, and owner.
     *
     * @param x The initial x-coordinate of the missile.
     * @param y The initial y-coordinate of the missile.
     * @param direction The firing direction of the missile.
     * @param owner The owner of the missile, which can be "player" or "enemy".
     */

    public Missile(double x, double y, Direction direction, String owner) {
        this.position = new Point2D(x, y);
        this.owner = owner;
        this.speed = 5.0; // Default speed, can be adjusted
        this.direction = direction; // Default direction is upward
        initGraphics();
    }

    /**
     * Initializes the graphics for the missile.
     * This method loads the appropriate image based on the missile's direction and sets its initial position.
     */
    private void initGraphics() {
        String imagePath = "/images/missile" + this.direction.name().toLowerCase() + ".gif";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.sprite = new ImageView(image);
        this.sprite.setX(position.getX());
        this.sprite.setY(position.getY());
    }

    /**
     * Moves the missile according to its direction and speed.
     * This method updates the missile's position based on its current direction and speed.
     */
    public void move() {
        // Basic missile movement logic based on direction
        switch (this.direction) {
            case UP:
                position = position.subtract(0, 5);
                break;
            case DOWN:
                position = position.add(0, 5);
                break;
            case LEFT:
                position = position.subtract(5, 0);
                break;
            case RIGHT:
                position = position.add(5, 0);
                break;
        }
        updatePosition();
    }

    /**
     * Updates the graphical position of the missile.
     * This method aligns the missile's graphical representation with its current logical position.
     */
    private void updatePosition() {
        sprite.setX(position.getX());
        sprite.setY(position.getY());
    }

    /**
     * Gets the missile's direction.
     *
     * @return The current direction of the missile.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the graphical representation of the missile.
     *
     * @return The ImageView object representing the missile.
     */
    public ImageView getSprite() {
        return sprite;
    }

    /**
     * Checks if the missile has moved out of the game boundaries.
     *
     * @return true if the missile is out of bounds, otherwise false.
     */
    public boolean isOutOfBounds() {
        // Check if the missile is out of the game pane boundaries
        return position.getY() < 0 || position.getY() > 600 || position.getX() < 0 || position.getX() > 800;
    }

    /**
     * Gets the boundaries of the missile in the parent node's coordinate system.
     *
     * @return A Bounds object representing the missile's boundaries.
     */
    public Bounds getBoundsInParent() {
        return sprite.getBoundsInParent();
    }

    /**
     * Gets the ImageView of the missile for display in the game pane.
     *
     * @return The ImageView of the missile.
     */
    public ImageView getView() {
        return sprite;
    }

    /**
     * Gets the owner of the missile.
     *
     * @return A string indicating the owner of the missile, either "player" or "enemy".
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets the current position of the missile.
     *
     * @return A Point2D object representing the missile's position.
     */
    public Point2D getPosition() {
        return position;
    }
}
