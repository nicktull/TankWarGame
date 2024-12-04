package edu.tcu.cs.tankwargame.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a medical pack in the Tank War Game that can be collected by the player to restore health.
 */
public class MedPack {
    private ImageView view;

    /**
     * Constructs a MedPack with a specified position within the game pane.
     *
     * @param x the x-coordinate where the medpack will be placed
     * @param y the y-coordinate where the medpack will be placed
     * @param gamePane the pane where the medpack will be added, allowing it to be displayed within the game
     */
    public MedPack(double x, double y, Pane gamePane) {
       Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/medpack.jpeg")));
       this.view = new ImageView(image);
       this.view.setX(x);
       this.view.setY(y);
       this.view.setFitWidth(30);
       this.view.setFitHeight(30);
       gamePane.getChildren().add(this.view);
    }

    /**
     * Initializes a list of MedPacks and places them at predefined positions within the game pane.
     *
     * @param gamePane the pane where all medpacks will be added
     * @return a list of initialized MedPacks
     */
    public static List<MedPack> setupMedPack(Pane gamePane){
        List<MedPack> medPacks = new ArrayList<>();
        medPacks.add(new MedPack(50,150,gamePane));
        medPacks.add(new MedPack(200,300,gamePane));
        medPacks.add(new MedPack(450,600,gamePane));
        medPacks.add(new MedPack(500,150,gamePane));
        return medPacks;
    }

    /**
     * Gets the ImageView of the medpack, which can be used to manipulate its properties or check its state.
     *
     * @return the ImageView representing the medpack
     */
    public ImageView getView() {
        return view;
    }


}
