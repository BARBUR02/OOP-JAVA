package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{

    protected Map<Vector2d,Animal> animals = new HashMap<>();


    public Map <Vector2d,Animal>  getAnimals() {
        return this.animals;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            if (animals.get(animal.getPosition())==null)
                animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null) return false;
        return true;
    }

    public void positionChanged(Vector2d oldPosition,Vector2d newPosition){
        Animal animal=animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition,animal);
    }

    public abstract Vector2d getLeftBottom();

    public abstract Vector2d getRightUp();

    public abstract boolean canMoveTo(Vector2d position);

    public abstract Object objectAt(Vector2d position);

    public String toString() {
        return new MapVisualizer((IWorldMap) this).draw(getLeftBottom(), getRightUp());
    }
}
