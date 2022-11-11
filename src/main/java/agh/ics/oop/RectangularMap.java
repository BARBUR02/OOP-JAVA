package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private int width;
    private int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Vector2d getLeftBottom() {
        return new Vector2d(0, 0);
    }

    @Override
    public Vector2d getRightUp() {
        return new Vector2d(width, height);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x <= width && position.x >= 0 &&
                position.y >= 0 && position.y <= height && !isOccupied(position);
    }



    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position))
                return animal;
        }
        return null;
    }

}
