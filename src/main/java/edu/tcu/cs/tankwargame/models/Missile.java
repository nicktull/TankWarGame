package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Missile {
    private Point2D position;
    private Direction direction;
    private final double speed;
    private String owner; // "player" or "enemy"
    private ImageView sprite;

    public Missile(double x, double y, Direction direction, String owner) {
        this.position = new Point2D(x, y);
        this.owner = owner;
        this.speed = 5.0; // Default speed, can be adjusted
        this.direction = direction; // Default direction is upward
        initGraphics();
    }

    private void initGraphics() {
        String imagePath = "/images/missile" + this.direction.name().toLowerCase() + ".gif";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.sprite = new ImageView(image);
        this.sprite.setX(position.getX());
        this.sprite.setY(position.getY());
    }

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

    private void updatePosition() {
        sprite.setX(position.getX());
        sprite.setY(position.getY());
    }

    public Direction getDirection() {
        return direction;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public boolean isOutOfBounds() {
        // Check if the missile is out of the game pane boundaries
        return position.getY() < 0 || position.getY() > 600 || position.getX() < 0 || position.getX() > 800;
    }

    public Bounds getBoundsInParent() {
        return sprite.getBoundsInParent();
    }


    public ImageView getView() {
        return sprite;
    }

    public String getOwner() {
        return owner;
    }

    public Point2D getPosition() {
        return position;
    }
}
