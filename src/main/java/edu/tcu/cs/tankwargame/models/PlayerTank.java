package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;

/**
 * Represents a player-controlled tank in the Tank War Game.
 * This class extends the abstract Tank class, implementing player-specific behaviors such as firing missiles with direction considerations.
 */
public class PlayerTank extends Tank {
    /**
     * Constructs a PlayerTank with specified position and direction.
     * @param x The initial x-coordinate of the tank.
     * @param y The initial y-coordinate of the tank.
     * @param direction The initial direction the tank is facing.
     */
    public PlayerTank(double x, double y, Direction direction) {
        super(x, y, direction);
    }

    /**
     * Initializes the graphical representation of the tank.
     */
    @Override
    public void initializeGraphics() {
        updateImage();
    }

    /**
     * Fires a missile from the tank. The missile's starting position is adjusted based on the tank's current direction.
     * @return A new Missile object representing the fired missile.
     */
    @Override
    public Missile fireMissile() {
        // Get the center of the tank
        double missileX = position.getX() + sprite.getImage().getWidth() / 2;
        double missileY = position.getY() + sprite.getImage().getHeight() / 2;

        // Adjust the missile's starting position based on the direction
        switch (direction) {
            case UP:
                missileY -= sprite.getImage().getHeight() / 2;
                break;
            case DOWN:
                missileY += sprite.getImage().getHeight() / 2;
                break;
            case LEFT:
                missileX -= sprite.getImage().getWidth() / 2;
                break;
            case RIGHT:
                missileX += sprite.getImage().getWidth() / 2;
                break;
        }

        return new Missile(missileX, missileY, direction, "player");
    }

    /**
     * Returns the bounds of the tank's sprite within its parent node.
     * @return The bounds of the sprite within the parent node.
     */
    public Bounds getBoundsInParent() {
        return sprite.getBoundsInParent();
    }

    /**
     * Decreases the health of the tank by 25 points.
     */
    public void setNegativeHealth() {
        health = health - 25;
    }

    /**
     * Increases the health of the tank by 25 points, up to a maximum of 100.
     */
    public void setPositiveHealth() {
        if (health < 100){
            health = health + 25;
        }
    }
}
