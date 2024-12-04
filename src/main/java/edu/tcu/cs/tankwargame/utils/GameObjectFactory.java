package edu.tcu.cs.tankwargame.utils;

import edu.tcu.cs.tankwargame.models.*;

/**
 * Factory class for creating game objects in the Tank War Game.
 * This class provides static methods to instantiate player and enemy tanks, and missiles with predefined configurations.
 */
public class GameObjectFactory {

    /**
     * Creates a player tank positioned at specified coordinates with a given direction.
     *
     * @param x the x-coordinate of the player tank's initial position.
     * @param y the y-coordinate of the player tank's initial position.
     * @param direction the initial direction the player tank is facing.
     * @return a new instance of PlayerTank with specified properties.
     */
    // Create a player tank with default settings
    public static PlayerTank createPlayerTank(double x, double y, Direction direction) {
        return new PlayerTank(x, y, direction);
    }

    /**
     * Creates an enemy tank positioned at specified coordinates with a given direction.
     *
     * @param x the x-coordinate of the enemy tank's initial position.
     * @param y the y-coordinate of the enemy tank's initial position.
     * @param direction the initial direction the enemy tank is facing.
     * @return a new instance of EnemyTank with specified properties.
     */
    // Create an enemy tank with default settings
    public static EnemyTank createEnemyTank(double x, double y, Direction direction) {
        return new EnemyTank(x, y, direction);
    }

    /**
     * Creates a missile at a specified position, moving in a specified direction, and owned by either a player or an enemy.
     *
     * @param x the x-coordinate where the missile will be created.
     * @param y the y-coordinate where the missile will be created, adjusted by -10 to appear above the firing tank.
     * @param direction the direction the missile will move towards.
     * @param owner the owner of the missile, indicating whether it's a player's or enemy's missile.
     * @return a new instance of Missile with specified properties.
     */
    // Create a missile with initial position, direction, and owner
    public static Missile createMissile(double x, double y, Direction direction, String owner) {
        // Assume missiles for both players and enemies move upwards initially; adjust if needed
        return new Missile(x, y - 10, direction, owner); // Offset y by -10 for missile starting position above the tank
    }
}
