package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javafx.scene.control.Label;
public class App extends Application implements IPositionChangeObserver{

//    MoveDirection[] directions ;
    private AbstractWorldMap map;
    private GridPane grid;

    private IEngine engine;
    private Thread engineThread;
    @Override
    public void init() throws Exception {
//        super.init();
        List<String> rawArgs= getParameters().getRaw ( );
        System.out.println("Inittin!");
        String[] args= rawArgs.toArray(new String[rawArgs.size()]);
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            ((GrassField)map).setObserver(this);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3)};
            this.engine = new SimulationEngine(directions, map, positions);
            ((SimulationEngine)this.engine).setMoveDelay(1000);
            engineThread = new Thread((SimulationEngine)engine);
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage)  throws Exception {

//        init();
        primaryStage.setTitle("Zwierzaki");
        GuiElementBox guiBoxCreator;
//        Label label = new Label("y/x");
//        Scene scene = new Scene(label,1200,1200);
        TextField input = new TextField();
        Button startBtn = new Button("Start game");


        this.grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setAlignment(Pos.CENTER);
        VBox central = new VBox(startBtn,input,grid);
        central.setAlignment(Pos.CENTER);
        central.setSpacing(5);
//        grid.setGridLinesVisible(true);

        startBtn.setOnAction(event -> {
            String moves= input.getText();
            if (moves.length()>0){
                update(moves.split(" "));
                try {
                    draw(grid);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            this.engineThread.start();
        });

        //Preparing grid layout
//        ColumnConstraints cs = new ColumnConstraints(50);
//        cs.setHalignment(HPos.CENTER);
//        RowConstraints rs = new RowConstraints(50);
//        rs.setValignment(VPos.CENTER);
//        grid.setGridLinesVisible(true);
        draw(grid);
        Scene scene =new Scene(central);
//        scene.
        primaryStage.setScene(scene);
        primaryStage.show();
//        engineThread.start();
    }

    public void update(String[] args){
        ((SimulationEngine) this.engine).setDirections(new OptionsParser().parse(args)) ;
        engineThread= new Thread((SimulationEngine)engine);;
    }
    public void draw(GridPane grid) throws FileNotFoundException {
        grid.setGridLinesVisible(false);
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        GuiElementBox guiBoxCreator;
        Vector2d leftBottom = map.getLeftBottom();
        Vector2d rightUp = map.getRightUp();
        System.out.println("Current borders");
        System.out.println(leftBottom);
        System.out.println(rightUp);
        int horizontalStart= leftBottom.x;
        int horizontalEnd = rightUp.x;
        int verticalStart= leftBottom.y;
        int verticalEnd = rightUp.y;

        //Preparing grid layout
        ColumnConstraints cs = new ColumnConstraints(50);
        cs.setHalignment(HPos.CENTER);
        RowConstraints rs = new RowConstraints(50);
        rs.setValignment(VPos.CENTER);
        grid.setGridLinesVisible(true);

        for (int i=0;i<horizontalEnd-horizontalStart+2;i++){
            grid.getColumnConstraints().add(cs);
        }
        for (int i=0;i<verticalEnd-verticalStart+2;i++){
            grid.getRowConstraints().add(rs);
        }
        grid.setGridLinesVisible(true);
        grid.add(new Label("y/x"),0,0);
        for (int i=1; i<verticalEnd-verticalStart+2;i++){
            grid.add(new Label(String.valueOf(i+verticalStart-1)),0,Math.abs(verticalEnd-verticalStart-i+2));

        }
        for (int i=1; i<horizontalEnd-horizontalStart+2;i++){
            grid.add(new Label(String.valueOf(i+horizontalStart-1)),i,0);
        }

        Map<Vector2d, Animal> animals = map.getAnimals();
//        System.out.println("Pozycje zwierzat :");
        for (Vector2d position : animals.keySet()){
//            System.out.println(position);
            guiBoxCreator = new GuiElementBox(animals.get(position));
            guiBoxCreator.addBox(grid,Math.abs(position.x-horizontalStart+1),Math.abs(verticalEnd-(position.y)+1));
//            grid.add(new Label((animals.get(position).toString())),position.x-horizontalStart+1,verticalEnd-(position.y)+1);
        }
        GrassField tmpmap = (GrassField) map;
        Map<Vector2d, Grass> bushes = tmpmap.getBushes();

//        System.out.println("Bushes");
        for (Vector2d position : bushes.keySet()){
//            System.out.println(position);
            guiBoxCreator = new GuiElementBox(bushes.get(position));
//            grid.add(new Label((bushes.get(position).toString())),position.x-horizontalStart+1,verticalEnd-(position.y)+1);
            guiBoxCreator.addBox(grid,Math.abs(position.x-horizontalStart+1),Math.abs(verticalEnd-(position.y)+1));
        }
        grid.setGridLinesVisible(true);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(()->{
            try {
                draw(this.grid);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}


