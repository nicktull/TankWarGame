package edu.tcu.cs.tankwargame.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedPack {
    private ImageView view;

    public MedPack(double x, double y, Pane gamePane) {
       Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/medpack.jpeg")));
       this.view = new ImageView(image);
       this.view.setX(x);
       this.view.setY(y);
       this.view.setFitWidth(30);
       this.view.setFitHeight(30);
       gamePane.getChildren().add(this.view);
    }

    public static List<MedPack> setupMedPack(Pane gamePane){
        List<MedPack> medPacks = new ArrayList<>();
        medPacks.add(new MedPack(50,150,gamePane));
        medPacks.add(new MedPack(200,300,gamePane));
        medPacks.add(new MedPack(450,600,gamePane));
        medPacks.add(new MedPack(500,150,gamePane));
        return medPacks;
    }

    public ImageView getView() {
        return view;
    }


}
