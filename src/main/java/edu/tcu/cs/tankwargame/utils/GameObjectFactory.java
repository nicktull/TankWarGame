package edu.tcu.cs.tankwargame.utils;

import edu.tcu.cs.tankwargame.models.*;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public class GameObjectFactory {

    // Create a player tank with default settings
    public static PlayerTank createPlayerTank(double x, double y, Direction direction) {
        return new PlayerTank(x, y, direction);
    }

    // Create an enemy tank with default settings
    public static EnemyTank createEnemyTank(double x, double y, Direction direction) {
        return new EnemyTank(x, y, direction);
    }

    // Create a missile with initial position, direction, and owner
    public static Missile createMissile(double x, double y, Direction direction, String owner) {
        // Assume missiles for both players and enemies move upwards initially; adjust if needed
        return new Missile(x, y - 10, direction, owner); // Offset y by -10 for missile starting position above the tank
    }
}
