package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
//import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {

    private IMapElement element;


    public GuiElementBox(IMapElement element) {
        this.element=element;
//        init();
    }

    public void addBox(GridPane grid, int i, int j) throws FileNotFoundException {
        Image image;
        Label label ;
        if (this.element instanceof Animal) {
            image = new Image(new FileInputStream(this.element.getAnimalBox(this.element.toString()) ));
        }
        else {
            image = new Image(new FileInputStream(this.element.getGrassBox() ));
        }
        label = new Label(this.element.toString()+" "+this.element.getPosition().toString() );

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        VBox vbox=new VBox();
        vbox.setSpacing(2);
        vbox.getChildren().add(imageView);
        vbox.getChildren().add(label);
        vbox.setMargin(label, new Insets(1,1,1,1));
        vbox.setAlignment(Pos.BASELINE_CENTER);
        grid.add(vbox, i,j);
//        return vbox;
    }

//    public void place(GridPane grid, int col, int row ){
//        grid.add(this.content,col,row);
//    }

}
