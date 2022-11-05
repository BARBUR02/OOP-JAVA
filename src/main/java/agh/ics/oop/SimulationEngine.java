package agh.ics.oop;

public class SimulationEngine implements IEngine{

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
        int animalCount=0;
        for (Vector2d position : initialPositions ){
            if(map.place(new Animal(map,position))) animalCount++;
        }
        RectangularMap newMap= (RectangularMap) map;
        for( int i=0;i<directions.length;i++){
            newMap.animals.get(i % animalCount).move(directions[i]);
        }

    }
}
