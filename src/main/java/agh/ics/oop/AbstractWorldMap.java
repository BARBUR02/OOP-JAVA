package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap {

    protected List<Animal> animals = new ArrayList<>();


    public List<Animal> getAnimals() {
        return this.animals;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null) return false;
        return true;
    }

    public abstract Vector2d getLeftBottom();

    public abstract Vector2d getRightUp();

    public abstract boolean canMoveTo(Vector2d position);

    public abstract Object objectAt(Vector2d position);

    public String toString() {
        return new MapVisualizer((IWorldMap) this).draw(getLeftBottom(), getRightUp());
    }
}
