package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    MoveDirection[] directions;
    IWorldMap map;
    Vector2d[] initialPositions;


    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.directions = directions;
        this.map = map;
        this.initialPositions = initialPositions;
    }

    @Override
    public void run() {
        int animalCount = 0;
        List<Animal> internalAnimals = new ArrayList<>();
        for (Vector2d position : initialPositions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                animalCount++;
                internalAnimals.add(animal);
            }

        }
//        RectangularMap newMap= (RectangularMap) map;
//        if (map instanceof GrassField) {
//             map = (GrassField) map;
//        }
//        if (map instanceof RectangularMap) {
//            map = (RectangularMap) map;
//        }
//        RectangularMap  newMap = (RectangularMap) map;
        for (int i = 0; i < directions.length; i++) {
            internalAnimals.get(i % animalCount).move(directions[i]);
        }

    }
}
