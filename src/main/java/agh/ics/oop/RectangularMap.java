package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private int width;
    private int height;

    public List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height=height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x<=width && position.x>=0 &&
        position.y>=0 && position.y<=height && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getCurrPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for( Animal animal: animals){
            if(animal.isAt(position))
                return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for( Animal animal: animals){
            if(animal.isAt(position))
                return animal;
        }
        return null;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0,0),new Vector2d(width,height));
    }
}
