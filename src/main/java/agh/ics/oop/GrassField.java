package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private int bushNumber;
    private int[] leftBottom;
    private int[] rightUp;
    private List<Grass> bushes;

    @Override
    public Vector2d getLeftBottom() {
        return new Vector2d(leftBottom[0], leftBottom[1]);
    }

    @Override
    public Vector2d getRightUp() {
        return new Vector2d(rightUp[0], rightUp[1]);
    }

    public GrassField(int bushNumber) {
        this.bushNumber = bushNumber;
        initBushes(bushNumber);
    }


    public void initBushes(int bushNumber) {
        if (bushNumber == 0) return;
        this.bushes = new ArrayList<Grass>();
//        int[] coordinates = randomCoordinates(bushNumber);
//        int[] coordinates = {2,-1};
//        Vector2d candidateVector = new Vector2d(coordinates[0], coordinates[1]);
//        bushes.add(new Grass(candidateVector));
//        leftBottom = new int[]{coordinates[0], coordinates[1]};
//        rightUp = new int[]{coordinates[0], coordinates[1]};
        for (int i = 0; i < bushNumber; i++) {
            bushes.add(new Grass(getNewPosition()));
        }
    }

    public Vector2d getNewPosition() {
        int[] coordinates = randomCoordinates(this.bushNumber);
        Vector2d candidateVector = new Vector2d(coordinates[0], coordinates[1]);
        while (isOccupied(candidateVector)) {
            coordinates = randomCoordinates(bushNumber);
            candidateVector = new Vector2d(coordinates[0], coordinates[1]);
        }
//        bushes.add(new Grass(candidateVector));
        updateEdges(coordinates);
        return candidateVector;
    }


    public void updateEdges(int[] x) {
        if (leftBottom == null || rightUp == null) {
            leftBottom = new int[]{x[0], x[1]};
            rightUp = new int[]{x[0], x[1]};
        } else {
            leftBottom[0] = min(leftBottom[0], x[0]);
            leftBottom[1] = min(leftBottom[1], x[1]);
            rightUp[0] = max(rightUp[0], x[0]);
            rightUp[1] = max(rightUp[1], x[1]);
        }
    }

    public int[] randomCoordinates(int n) {
        Random generator = new Random();
        return new int[]{generator.nextInt((int) Math.sqrt(n * 10) + 1),
                generator.nextInt((int) Math.sqrt(n * 10) + 1)};
    }

    public List<Grass> getBushes() {
        return bushes;
    }

    public void moveGrass(Vector2d position) {
        Grass toChange;
        for (Grass bush : bushes) {
            if (bush.getPosition().equals(position)) {
                toChange = bush;
                toChange.setPosition(getNewPosition());
                return;
            }
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object mapElement = objectAt(position);
        if ((mapElement instanceof Animal)) return false;
        if ((mapElement instanceof Grass)) {
//            System.out.println("PRZED:\n-----\n"+this);
//            System.out.println("Pozycja przed: " + position);
            Grass grassElement = (Grass) mapElement;
            grassElement.setPosition(getNewPosition());
//            System.out.println("PO:\n----\n"+this);
//            System.out.println("Zmieniona pozycja: "+grassElement.getPosition());
        }
        int[] coordinates = {position.x, position.y};
        updateEdges(coordinates);
        return true;
    }


    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position))
                return animal;
        }
        for (Grass bush : bushes) {
            if (bush.getPosition().equals(position))
                return bush;
        }

        return null;
    }


}
