package agh.ics.oop;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class GrassFieldTest {
    @Test
    void initTest() {
        try {
            IWorldMap checkMap = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3), new Vector2d(2, 2),
                    new Vector2d(3, 3)};
            IEngine engine1 = new SimulationEngine(new OptionsParser().parse(new String[]{""}), checkMap, positions);
            engine1.run();
            GrassField checkMap1 = (GrassField) checkMap;


            Map<Vector2d, Animal> animals = (checkMap1).getAnimals();
            Map<Vector2d, Grass> bushes = (checkMap1).getBushes();
            assertTrue(bushes.size() == 10 && checkMap1.objectAt(new Vector2d(2, 2)) instanceof Animal
                    && checkMap1.objectAt(new Vector2d(3, 3)) instanceof Animal
            );
        }
        catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    void blockTest() {
        try{
        IWorldMap checkMap = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3)};
        IEngine engine1 = new SimulationEngine(new OptionsParser().parse(new String[]{"f", "l", "l", " f"}), checkMap, positions);
        engine1.run();
        GrassField checkMap1 = (GrassField) checkMap;
        Map<Vector2d, Animal> animals = (checkMap1).getAnimals();
        Map<Vector2d, Grass> bushes = (checkMap1).getBushes();
        assertTrue(bushes.size() == 10
                && checkMap1.objectAt(new Vector2d(2, 3)) instanceof Animal
                && checkMap1.objectAt(new Vector2d(3, 3)) instanceof Animal
                && animals.get(new Vector2d(2, 3)).getOrient() == MapDirection.WEST
                && animals.get(new Vector2d(3, 3)).getOrient() == MapDirection.WEST
        );

    }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    void borderTest() {
        IWorldMap checkMap = new GrassField(10);
        Vector2d[] positions = {new Vector2d(-1, -2), new Vector2d(10, 10)};
        IEngine engine1 = new SimulationEngine(new OptionsParser().parse(new String[]{"b", "f", "b", "r", "l", "f", "f"}), checkMap, positions);
        engine1.run();
        GrassField checkMap1 = (GrassField) checkMap;
        assertTrue(checkMap1.getLeftBottom().equals(new Vector2d(-2, -4))
                && checkMap1.getRightUp().equals(new Vector2d(11, 11))
        );
    }

    @Test
    void complexRouteTest() {
        IWorldMap checkMap = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine1 = new SimulationEngine(new OptionsParser().parse(
                new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"})
                , checkMap, positions);
        engine1.run();
//        System.out.println(checkMap);
        GrassField checkMap1 = (GrassField) checkMap;
        Map<Vector2d,Animal> animals = checkMap1.getAnimals();
        Map<Vector2d,Grass> bushes =checkMap1.getBushes();
//        System.out.println(checkMap);
        assertTrue(bushes.size() == 10
                && animals.get(new Vector2d(2, -1)).isAt(new Vector2d(2, -1))
                && animals.get(new Vector2d(3, 7)).isAt(new Vector2d(3, 7))
                && animals.get(new Vector2d(2, -1)).getOrient() == MapDirection.SOUTH
                && animals.get(new Vector2d(3, 7)).getOrient() == MapDirection.NORTH
                && (checkMap1.objectAt(new Vector2d(2,-1)) instanceof Animal)
                && (checkMap1.objectAt(new Vector2d(3,7)) instanceof Animal));


    }

    @Test
    void complexRouteThreeAnimalsTest() {
        IWorldMap checkMap = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(5, 4)};
        IEngine engine1 = new SimulationEngine(new OptionsParser().parse(
                new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"})
                , checkMap, positions);
        engine1.run();
        GrassField checkMap1 = (GrassField) checkMap;
        Map<Vector2d,Animal> animals = (checkMap1).getAnimals();
        Map<Vector2d,Grass> bushes = (checkMap1).getBushes();
//        animals.add(new Animal(MapDirection.NORTH, correctMap1, new Vector2d(2, 5)));
//        animals.add(new Animal(MapDirection.EAST, correctMap1, new Vector2d(9, 4)));
//        animals.add(new Animal(MapDirection.EAST, correctMap1, new Vector2d(10, 4)));
        assertTrue(bushes.size() == 10
                && animals.get(new Vector2d(2, 9)).isAt(new Vector2d(2, 9))
                && animals.get(new Vector2d(9, 4)).isAt(new Vector2d(9, 4))
                && animals.get(new Vector2d(13, 4)).isAt(new Vector2d(13, 4))
                && animals.get(new Vector2d(2, 9)).getOrient() == MapDirection.NORTH
                && animals.get(new Vector2d(9, 4)).getOrient() == MapDirection.EAST
                && animals.get(new Vector2d(13, 4)).getOrient() == MapDirection.EAST
                && (checkMap1.objectAt(new Vector2d(2, 9)) instanceof Animal)
                && (checkMap1.objectAt(new Vector2d(9, 4)) instanceof Animal)
                && (checkMap1.objectAt(new Vector2d(13, 4)) instanceof Animal));

    }
}




