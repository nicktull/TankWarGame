package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.Random;

/**
 * Represents an enemy tank in the Tank War Game.
 * This class extends the abstract Tank class, implementing enemy-specific behaviors such as autonomous movement and firing.
 */
public class EnemyTank extends Tank {
    private Random random = new Random();
    private long lastFireTime = 0;
    private long fireDelay = 3000;

    /**
     * Constructs an EnemyTank with specified position and direction.
     * @param x The initial x-coordinate of the tank.
     * @param y The initial y-coordinate of the tank.
     * @param direction The initial direction the tank is facing.
     */
    public EnemyTank(double x, double y, Direction direction) {
        super(x, y, direction);
    }

    /**
     * Initializes the graphical representation of the tank.
     */
    @Override
    protected void initializeGraphics() {
        updateImage();
    }

    /**
     * Fires a missile if the fire delay has elapsed since the last missile was fired.
     * @return A new Missile object if fired, null otherwise.
     */
    @Override
    public Missile fireMissile() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFireTime >= fireDelay) {
            lastFireTime = currentTime; // Update the last fire time
            double missileX = position.getX() + sprite.getImage().getWidth() / 2;
            double missileY = position.getY() + sprite.getImage().getHeight();
            return new Missile(missileX, missileY, direction, "enemy");
        }
        return null;
    }

    /**
     * Updates the behavior of the tank, including movement and firing missiles.
     * @param walls A list of Wall objects to consider for collision detection.
     * @param gamePane The game pane in which the tank operates, used for adding missile views if fired.
     */
    // This method updates the tank's behavior each frame
    public void update(List<Wall> walls, Pane gamePane) {
        // Randomly decide to move or change direction
        if (random.nextInt(10) == 0) {  // 10% chance to change direction
            Direction[] directions = Direction.values();
            setDirection(directions[random.nextInt(directions.length)]);
        }

        // Random movement logic
        double dx = 0;
        double dy = 0;
        switch (direction) {
            case UP: dy = -2; break;
            case DOWN: dy = 2; break;
            case LEFT: dx = -2; break;
            case RIGHT: dx = 2; break;
        }
        move(dx, dy, walls, gamePane);  // Move in the current direction

        // Attempt to fire a missile
        Missile missile = fireMissile();
        if (missile != null) {
            gamePane.getChildren().add(missile.getView());
        }
    }

    /**
     * Returns the bounds of the tank's sprite within its parent node.
     * @return The bounds of the sprite within the parent node.
     */
    public Bounds getBoundsInParent() {
        return sprite.getBoundsInParent();
    }
}
