package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Tank {
    private Random random = new Random();
    private long lastFireTime = 0;
    private long fireDelay = 3000;

    public EnemyTank(double x, double y, Direction direction) {
        super(x, y, direction);
    }

    @Override
    protected void initializeGraphics() {
        updateImage();
    }

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

    public Bounds getBoundsInParent() {
        return sprite.getBoundsInParent();
    }
}
