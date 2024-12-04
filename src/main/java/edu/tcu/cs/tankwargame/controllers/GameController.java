package edu.tcu.cs.tankwargame.controllers;

import edu.tcu.cs.tankwargame.models.*;
import edu.tcu.cs.tankwargame.utils.GameObjectFactory;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static edu.tcu.cs.tankwargame.models.Wall.setupWalls;

public class GameController {

    @FXML
    private Pane gamePane;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label enemyCountLabel;

    @FXML
    private ProgressBar healthBar;

    private PlayerTank playerTank;
    private List<EnemyTank> enemyTanks;
    private List<Missile> missiles;
    private List<Wall> walls;
    private AnimationTimer gameLoop;
    private int score = 0;

    public void initialize() {
        setupGame();
        walls = Wall.setupWalls(gamePane);
        startGameLoop();
    }

    private void setupGame() {
        try {
            playerTank = GameObjectFactory.createPlayerTank(100, 100, Direction.UP);
            if (playerTank.getView() != null) {
                playerTank.initializeGraphics();
                gamePane.getChildren().add(playerTank.getView());
                walls = new ArrayList<>();
                setupWalls(gamePane);
            } else {
                System.out.println("Failed to initialize the player tank or its view.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // This will print more detailed information if initialization fails
        }

        // Setup enemy tanks
        enemyTanks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EnemyTank enemy = GameObjectFactory.createEnemyTank(50 + i * 100, 50, Direction.DOWN);
            enemyTanks.add(enemy);
            gamePane.getChildren().add(enemy.getView());
        }

        // Setup missiles
        missiles = new CopyOnWriteArrayList<>();

    }

    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame(walls);
            }
        };
        gameLoop.start();
    }

    public void setupEventHandlers(Scene scene) {
        // Handling key events for player input
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        playerTank.setDirection(Direction.UP);
                        playerTank.move(0, -10,walls, gamePane);
                        break;
                    case DOWN:
                        playerTank.setDirection(Direction.DOWN);
                        playerTank.move(0, 10,walls, gamePane);
                        break;
                    case LEFT:
                        playerTank.setDirection(Direction.LEFT);
                        playerTank.move(-10, 0,walls, gamePane);
                        break;
                    case RIGHT:
                        playerTank.setDirection(Direction.RIGHT);
                        playerTank.move(10, 0,walls, gamePane);
                        break;
                    case SPACE:
                        Missile missile = playerTank.fireMissile();
                        missiles.add(missile);
                        gamePane.getChildren().add(missile.getView());
                        break;
                }
            }
        });
    }

    private void updateGame(List<Wall> walls) {
        // Update player and enemy movements, missile movements, and check collisions
        for (Missile missile : missiles) {
            missile.move();
            if (missile.isOutOfBounds()) {
                new Explosion(missile.getPosition(), gamePane, 1000); // Consider adding boundary conditions for explosion
                missiles.remove(missile);
                gamePane.getChildren().remove(missile.getView());
            }
        }

        // Update enemy tank behavior
        for (EnemyTank enemy : enemyTanks) {
            Missile enemyMissile = enemy.fireMissile();
            if (enemyMissile != null) {
                missiles.add(enemyMissile);
                gamePane.getChildren().add(enemyMissile.getView());
            }
            enemy.update(walls, gamePane);
        }
        checkCollisions();
        // Check if all enemy tanks have been destroyed
        if (enemyTanks.isEmpty()) {
            gameLoop.stop(); // Stop the game loop
            showGameOverPopupWin(); // Show game over popup
        }
        updateUI();
    }

    private void showGameOverPopupWin() {
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Congratulations!");
            alert.setContentText("You have destroyed all enemy tanks!");
            alert.setOnHidden(evt -> onExitPressed()); // Optional: call onExitPressed() when alert is closed
            alert.show();
        });
    }

    private void showGameOverPopupLose(){
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("You lost!");
            alert.setContentText("You have been destroyed!");
            alert.setOnHidden(evt -> onExitPressed());
            alert.show();
        });
    }

    private void checkCollisions() {
        // Check for collisions between each missile and enemy tanks
        List<Missile> missilesToRemove = new ArrayList<>();
        List<EnemyTank> tanksToRemove = new ArrayList<>();

        for (Missile missile : missiles) {
            // Check for missile collisions with enemy tanks
            for (EnemyTank tank : enemyTanks) {
                if (missile.getOwner().equals("player") && missile.getBoundsInParent().intersects(tank.getBoundsInParent())) {
                    tanksToRemove.add(tank);
                    missilesToRemove.add(missile);
                    increaseScore();
                    // Trigger explosion at the tank's location
                    new Explosion(tank.getPosition(), gamePane, 1000);
                }
            }

            // Check if this missile hits the player tank
            if (missile.getOwner().equals("enemy") && missile.getBoundsInParent().intersects(playerTank.getBoundsInParent())) {
                missilesToRemove.add(missile);
                if (playerTank.getHealth() <= 25){
                    new Explosion(playerTank.getPosition(), gamePane, 1000);
                    showGameOverPopupLose(); // You might want to call a different method if the player loses
                } else {
                    playerTank.setHealth();
                }
            }

            // Check for missile collisions with walls
            for (Wall wall : walls) {
                if (missile.getBoundsInParent().intersects(wall.getView().getBoundsInParent())) {
                    missilesToRemove.add(missile);
                    new Explosion(missile.getPosition(), gamePane, 1000);
                }
            }
        }

        // Remove collided missiles and tanks
        missiles.removeAll(missilesToRemove);
        enemyTanks.removeAll(tanksToRemove);
        missilesToRemove.forEach(missile -> gamePane.getChildren().remove(missile.getView()));
        tanksToRemove.forEach(tank -> gamePane.getChildren().remove(tank.getView()));
    }

    private void increaseScore(){
        this.score += 10;
    }

    private void updateUI() {
        healthBar.setProgress(playerTank.getHealth() / 100.0);
        scoreLabel.setText("Score: " + score);
        enemyCountLabel.setText("Enemy tanks left: " + enemyTanks.size());
    }

    @FXML
    private void onExitPressed() {
        gameLoop.stop(); // Stop the game loop
        System.exit(0);  // Exit the application
    }

//    private void takeDamage(){
//        healthbar = healthbar - 10;
//    }
}
