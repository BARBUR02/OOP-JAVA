package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javafx.scene.control.Label;
public class App extends Application{

    private AbstractWorldMap map;

    @Override
    public void init() throws Exception {
        super.init();
        List<String> rawArgs= getParameters().getRaw ( );
        String[] args= rawArgs.toArray(new String[rawArgs.size()]);
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        init();

        Label label = new Label("y/x");
        Scene scene = new Scene(label,1200,1200);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setGridLinesVisible(true);
        primaryStage.setTitle("Zwierzaki");


        Vector2d leftBottom = map.getLeftBottom();
        Vector2d rightUp = map.getRightUp();
        System.out.println(leftBottom);
        System.out.println(rightUp);
        int horizontalStart= leftBottom.x;
        int horizontalEnd = rightUp.x;
        int verticalStart= leftBottom.y;
        int verticalEnd = rightUp.y;

        //Preparing grid layout
        ColumnConstraints cs = new ColumnConstraints(20);
        cs.setHalignment(HPos.CENTER);
        RowConstraints rs = new RowConstraints(20);
        rs.setValignment(VPos.CENTER);

        for (int i=0;i<horizontalEnd-horizontalStart+2;i++){
            grid.getColumnConstraints().add(cs);
        }
        for (int i=0;i<verticalEnd-verticalStart+2;i++){
            grid.getRowConstraints().add(rs);
        }

        grid.add(new Label("y/x"),0,0);
        for (int i=1; i<verticalEnd-verticalStart+2;i++){
            grid.add(new Label(String.valueOf(i+verticalStart-1)),0,verticalEnd-verticalStart-i+2);
        }
        for (int i=1; i<horizontalEnd-horizontalStart+2;i++){
            grid.add(new Label(String.valueOf(i+horizontalStart-1)),i,0);
        }

        Map<Vector2d, Animal> animals = map.getAnimals();
        System.out.println("Pozycje zwierzat :");
        for (Vector2d position : animals.keySet()){
            System.out.println(position);
            grid.add(new Label((animals.get(position).toString())),position.x-horizontalStart+1,verticalEnd-(position.y)+1);
        }
        GrassField tmpmap = (GrassField) map;
        Map<Vector2d, Grass> bushes = tmpmap.getBushes();

        System.out.println("Bushes");
        for (Vector2d position : bushes.keySet()){
            System.out.println(position);
            grid.add(new Label((bushes.get(position).toString())),position.x-horizontalStart+1,verticalEnd-(position.y)+1);
        }

        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }

}
