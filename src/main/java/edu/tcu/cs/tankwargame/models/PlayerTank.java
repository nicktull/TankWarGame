package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;

public class PlayerTank extends Tank {
    public PlayerTank(double x, double y, Direction direction) {
        super(x, y, direction);
    }

    @Override
    public void initializeGraphics() {
        updateImage();
    }

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

    public Bounds getBoundsInParent() {
        return sprite.getBoundsInParent();
    }
}
