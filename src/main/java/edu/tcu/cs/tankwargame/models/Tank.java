package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.Objects;

/**
 * Represents a generic tank in the Tank War Game.
 * This class provides a base for different types of tanks by defining their common properties and behaviors.
 */
public abstract class Tank {
    protected Point2D position;
    protected Direction direction;
    protected int health;
    protected ImageView sprite;

    /**
     * Constructs a new tank with specified initial position and direction.
     * @param x The initial x-coordinate of the tank.
     * @param y The initial y-coordinate of the tank.
     * @param direction The initial direction of the tank.
     */
    public Tank(double x, double y, Direction direction) {
        this.position = new Point2D(x, y);
        this.direction = direction;
        health = 100; // Default health value for new tanks
        sprite = new ImageView();
        initializeGraphics();
    }

    /**
     * Sets the direction of the tank and updates its image accordingly.
     * @param newDirection The new direction for the tank.
     */
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
        updateImage();
    }

    /**
     * Updates the image of the tank based on its direction.
     */
    protected void updateImage() {
        String imagePath = "/images/tank" + this.direction.name().toLowerCase() + ".gif";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        if ( sprite != null ) {
            sprite.setImage(image);
            sprite.setX(position.getX());
            sprite.setY(position.getY());
        }
    }

    /**
     * Moves the tank to a new position if it is not blocked by walls or out of the game pane's boundaries.
     * @param dx The change in x-coordinate.
     * @param dy The change in y-coordinate.
     * @param walls The list of walls to check for collisions.
     * @param gamePane The pane representing the game area to check for boundaries.
     */
    public void move(double dx, double dy, List<Wall> walls, Pane gamePane) {
        // Calculate new position
        double newX = position.getX() + dx;
        double newY = position.getY() + dy;

        //Boundary conditions
        double maxWidth = gamePane.getWidth();
        double maxHeight = gamePane.getHeight();
        double tankWidth = sprite.getImage().getWidth();
        double tankHeight = sprite.getImage().getHeight();

        // Check horizontal boundaries
        if (newX < 0) newX = 0; // Prevent moving off left edge
        else if (newX + tankWidth > maxWidth) newX = maxWidth - tankWidth; // Prevent moving off right edge

        // Check vertical boundaries
        if (newY < 0) newY = 0; // Prevent moving off top edge
        else if (newY + tankHeight > maxHeight) newY = maxHeight - tankHeight; // Prevent moving off bottom edge

        // Create a temporary ImageView to test the new position without actually moving
        ImageView tempSprite = new ImageView(sprite.getImage());
        tempSprite.setX(newX);
        tempSprite.setY(newY);

        // Check if wall is in way of tank, will not update position if it is
        for (Wall wall : walls) {
            Bounds wallBounds = wall.getView().getBoundsInParent();
            if (tempSprite.getBoundsInParent().intersects(wallBounds)) {
                return;
            }
        }

        // Update position if no collision
        position = new Point2D(newX, newY);
        sprite.setX(newX);
        sprite.setY(newY);
    }

    /**
     * Gets the current position of the tank.
     * @return The current position as a {@link Point2D}.
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Initializes the graphics of the tank. This method must be implemented by subclasses to set up the tank's sprite.
     */
    protected abstract void initializeGraphics();

    /**
     * Fires a missile from the tank. This method must be implemented by subclasses to create and return a new missile.
     * @return The new missile fired by the tank.
     */
    public abstract Missile fireMissile();

    /**
     * Gets the graphical view of the tank.
     * @return The {@link ImageView} of the tank.
     */
    public ImageView getView(){
        return sprite;
    }

    /**
     * Gets the current health of the tank.
     * @return The health value of the tank.
     */
    public int getHealth() {
        return health;
    }

}
