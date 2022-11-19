package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private MapDirection orient;
    private Vector2d position;

    private IWorldMap map;

    private List<IPositionChangeObserver> observers=new ArrayList<>();
    public Animal() {
        this.orient = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }


    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.orient = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        addObserver((IPositionChangeObserver) map);
    }

    public Animal(MapDirection direction,IWorldMap map, Vector2d initialPosition) {
        this.orient = direction;
        this.position = initialPosition;
        this.map = map;
        addObserver((IPositionChangeObserver) map);
    }

    public Animal(IWorldMap map) {
        this.orient = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
        addObserver((IPositionChangeObserver) map);
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition,Vector2d newPosition){
        for (IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition,newPosition);
        }
    }

    public MapDirection getOrient() {
        return orient;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return orient.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d vector = this.position;
//        if (direction==MoveDirection.FORWARD || direction==MoveDirection.BACKWARD) {
//            if (vector.x <= 0 && this.orient == MapDirection.WEST && direction==MoveDirection.FORWARD ||
//                    vector.x >= 4 && this.orient == MapDirection.EAST && direction==MoveDirection.FORWARD)
//                return;
//            if (vector.x >= 4 && this.orient == MapDirection.WEST && direction==MoveDirection.BACKWARD ||
//                    vector.x <= 0 && this.orient == MapDirection.EAST && direction==MoveDirection.BACKWARD)
//                return;
//            if (vector.y <= 0 && this.orient == MapDirection.SOUTH && direction==MoveDirection.FORWARD||
//                    vector.y >= 4 && this.orient == MapDirection.NORTH && direction==MoveDirection.FORWARD)
//                return;
//            if (vector.y >= 4 && this.orient == MapDirection.SOUTH && direction==MoveDirection.BACKWARD||
//                    vector.y <= 0 && this.orient == MapDirection.NORTH && direction==MoveDirection.BACKWARD)
//                return;
//        }
        switch (direction) {
            case RIGHT -> {
                this.orient = this.orient.next();
            }
            case LEFT -> this.orient = this.orient.previous();
            case FORWARD -> {
                switch (this.orient) {
                    case NORTH -> {
                        Vector2d candidatePosition = new Vector2d(vector.x, vector.y + 1);
                        if (map.canMoveTo(candidatePosition)){
                            positionChanged(this.position,candidatePosition);
                            this.position = candidatePosition;
                        }
                    }
                    case SOUTH -> {
                        Vector2d candidatePosition = new Vector2d(vector.x, vector.y - 1);
                        if (map.canMoveTo(candidatePosition)){
                            positionChanged(this.position,candidatePosition);
                            this.position = candidatePosition;
                        }
                    }
                    case EAST -> {
                        Vector2d candidatePosition = new Vector2d(vector.x + 1, vector.y);
                        if (map.canMoveTo(candidatePosition)) {
                            positionChanged(this.position, candidatePosition);
                            this.position = candidatePosition;
                        }
                    }
                    case WEST -> {
                        Vector2d candidatePosition = new Vector2d(vector.x - 1, vector.y);
                        if (map.canMoveTo(candidatePosition)){
                            positionChanged(this.position,candidatePosition);
                            this.position = candidatePosition;
                        }
                    }
                }
            }
            case BACKWARD -> {
                switch (this.orient) {
                    case NORTH -> {
                        Vector2d candidatePosition = new Vector2d(vector.x, vector.y - 1);
                        if (map.canMoveTo(candidatePosition)){
                            positionChanged(this.position,candidatePosition);
                            this.position = candidatePosition;
                        }
                    }
                    case SOUTH -> {
                        Vector2d candidatePosition = new Vector2d(vector.x, vector.y + 1);
                        if (map.canMoveTo(candidatePosition)){
                            positionChanged(this.position,candidatePosition);
                            this.position = candidatePosition;
                        }
                    }
                    case EAST -> {
                        Vector2d candidatePosition = new Vector2d(vector.x - 1, vector.y);
                        if (map.canMoveTo(candidatePosition)){
                            positionChanged(this.position,candidatePosition);
                            this.position = candidatePosition;
                        }
                    }
                    case WEST -> {
                        Vector2d candidatePosition = new Vector2d(vector.x + 1, vector.y);
                        if (map.canMoveTo(candidatePosition)){
                            positionChanged(this.position,candidatePosition);
                            this.position = candidatePosition;
                        }

                    }
                }
            }

        }
        System.out.println(this.position);
    }

}
