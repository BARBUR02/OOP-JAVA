package agh.ics.oop;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GrassField extends AbstractWorldMap {
    private int bushNumber;
    private int[] leftBottom;
    private int[] rightUp;
    private Map<Vector2d,Grass> bushes;

    @Override
    public Vector2d getLeftBottom() {
        return new Vector2d(boundary.getFirstX(),boundary.getFirstY());
    }

//    @Override
//    public Vector2d getLeftBottom() {
//        return new Vector2d(leftBottom[0], leftBottom[1] );
//    }
    @Override
    public Vector2d getRightUp() {
        return new Vector2d(boundary.getLastX(), boundary.getLastY());
    }

    public GrassField(int bushNumber) {
        this.bushNumber = bushNumber;
        initBushes(bushNumber);
    }


    public void initBushes(int bushNumber) {
        if (bushNumber == 0) return;
        this.bushes = new HashMap<Vector2d,Grass>();
//        int[] coordinates = randomCoordinates(bushNumber);
//        int[] coordinates = {2,-1};
//        Vector2d candidateVector = new Vector2d(coordinates[0], coordinates[1]);
//        bushes.add(new Grass(candidateVector));
//        leftBottom = new int[]{coordinates[0], coordinates[1]};
//        rightUp = new int[]{coordinates[0], coordinates[1]};
        for (int i = 0; i < bushNumber; i++) {
            Vector2d position=getNewPosition();
            Grass grass=new Grass(position);
            bushes.put(position,grass);
            boundary.addToMap(position, grass);
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

    public Map<Vector2d,Grass> getBushes() {
        return bushes;
    }

    public void moveGrass(Vector2d position) {
        Grass toChange;
        if (bushes.get(position) != null ) {
                toChange = bushes.get(position);
                Vector2d vector=getNewPosition();
                toChange.setPosition(vector);
                bushes.remove(position);
                bushes.put(vector,toChange);
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position){
        Object mapElement = objectAt(position);
        if ((mapElement instanceof Animal)) return false;
        if ((mapElement instanceof Grass)) {
//            System.out.println("PRZED:\n-----\n"+this);
//            System.out.println("Pozycja przed: " + position);
//            animals.remove(animal.getPosition());
            Vector2d vector=getNewPosition();
            bushes.remove(position);
            boundary.removeKey(position);
            Grass grassElement = (Grass) mapElement;
            grassElement.setPosition(vector);
            bushes.put(vector,grassElement);
            boundary.addToMap(vector, grassElement);

//            System.out.println("PO:\n----\n"+this);
//            System.out.println("Zmieniona pozycja: "+grassElement.getPosition());
        }
        int[] coordinates = {position.x, position.y};
        updateEdges(coordinates);
        return true;
    }


    @Override
    public Object objectAt(Vector2d position) {
        if (animals.get(position)!=null){
            return animals.get(position);
        }

        if (bushes.get(position)!=null){
            return bushes.get(position);
        }

        return null;
    }


}
