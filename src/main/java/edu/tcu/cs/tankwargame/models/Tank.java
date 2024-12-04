package edu.tcu.cs.tankwargame.models;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.Objects;


public abstract class Tank {
    protected Point2D position;
    protected Direction direction;
    protected int health;
    protected ImageView sprite;

    public Tank(double x, double y, Direction direction) {
        this.position = new Point2D(x, y);
        this.direction = direction;
        health = 100; // Default health value for new tanks
        sprite = new ImageView();
        initializeGraphics();
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
        updateImage();
    }

    protected void updateImage() {
        String imagePath = "/images/tank" + this.direction.name().toLowerCase() + ".gif";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        if ( sprite != null ) {
            sprite.setImage(image);
            sprite.setX(position.getX());
            sprite.setY(position.getY());
        }
    }

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

    public Point2D getPosition() {
        return position;
    }

    protected abstract void initializeGraphics();

    public abstract Missile fireMissile();

    public ImageView getView(){
        return sprite;
    }

    public int getHealth() {
        return health;
    }

}
