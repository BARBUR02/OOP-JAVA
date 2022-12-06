package agh.ics.oop;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    MoveDirection[] directions;
    IWorldMap map;
    Vector2d[] initialPositions;

    int animalCount;
    List<Animal>  internalAnimals;
    int MoveDelay;

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.directions = directions;
        this.map = map;
        this.initialPositions = initialPositions;



        animalCount = 0;
        internalAnimals = new ArrayList<>();
        for (Vector2d position : initialPositions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                animalCount++;
                internalAnimals.add(animal);
            }

        }

    }

    public void setMoveDelay(int moveDelay) {
        this.MoveDelay = moveDelay;
    }

    @Override
    public void run() {
//        int animalCount = 0;
//        List<Animal> internalAnimals = new ArrayList<>();
//        for (Vector2d position : initialPositions) {
//            Animal animal = new Animal(map, position);
//            if (map.place(animal)) {
//                animalCount++;
//                internalAnimals.add(animal);
//            }
//
//        }
//        RectangularMap newMap= (RectangularMap) map;
//        if (map instanceof GrassField) {
//             map = (GrassField) map;
//        }
//        if (map instanceof RectangularMap) {
//            map = (RectangularMap) map;
//        }
//        RectangularMap  newMap = (RectangularMap) map;
        for (int i = 0; i < directions.length; i++) {
            try{
                Thread.sleep(this.MoveDelay);
            } catch (InterruptedException e){
                System.out.println(e);
            }

            internalAnimals.get(i % animalCount).move(directions[i]);
        }

    }


}
