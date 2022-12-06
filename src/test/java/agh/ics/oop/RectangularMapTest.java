package agh.ics.oop;


import org.junit.jupiter.api.Test;


import java.nio.channels.IllegalChannelGroupException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RectangularMapTest {

    @Test
    void initTest(){
        try {
            IWorldMap checkMap = new RectangularMap(10, 5);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3), new Vector2d(2, 2),
                    new Vector2d(3, 3)};
            IEngine engine1 = new SimulationEngine(new OptionsParser().parse(new String[]{""}), checkMap, positions);
            engine1.run();
            IWorldMap correctMap = new RectangularMap(10, 5);
            RectangularMap correctMap1 = (RectangularMap) correctMap;
            Map<Vector2d, Animal> animals = correctMap1.getAnimals();
            Vector2d vec1 = new Vector2d(2, 2);
            Vector2d vec2 = new Vector2d(3, 3);
            animals.put(vec1, new Animal(correctMap1, vec1));
            animals.put(vec2, new Animal(correctMap1, vec2));
            assertTrue(correctMap1.toString().equals(checkMap.toString()));
        }
        catch (IllegalArgumentException e){
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    void blockTest(){
        try {
            IWorldMap checkMap = new RectangularMap(10, 5);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 3)};
            IEngine engine1 = new SimulationEngine(new OptionsParser().parse(new String[]{"ff", "l", "l", " f"}), checkMap, positions);
            engine1.run();
            IWorldMap correctMap = new RectangularMap(10, 5);
            RectangularMap correctMap1 = (RectangularMap) correctMap;
            Map<Vector2d, Animal> animals = correctMap1.getAnimals();
            Vector2d vec1 = new Vector2d(2, 3);
            Vector2d vec2 = new Vector2d(3, 3);
            animals.put(vec1, new Animal(MapDirection.WEST, correctMap1, vec1));
            animals.put(vec2, new Animal(MapDirection.WEST, correctMap1, vec2));
//        animals.add(new Animal(MapDirection.WEST,correctMap1,new Vector2d(2,3)));
//        animals.add(new Animal(MapDirection.WEST,correctMap1,new Vector2d(3,3)));
            assertTrue(correctMap1.toString().equals(checkMap.toString()));
        }
        catch (IllegalArgumentException e){
            assertTrue(e instanceof IllegalArgumentException);
        }

    }

    @Test
    void borderTest(){
        IWorldMap checkMap=new RectangularMap(10,5);
        Vector2d[] positions = {new Vector2d(9,1), new Vector2d(2,2)};
        IEngine engine1=new SimulationEngine(new OptionsParser().parse(new String[] {"b", "b", "b","b","b", "b","b","r","r"})
                ,checkMap,positions);
        engine1.run();
        IWorldMap correctMap= new RectangularMap(10,5);
        RectangularMap correctMap1 = (RectangularMap) correctMap;
        Map<Vector2d,Animal> animals = correctMap1.getAnimals();
        Vector2d vec1=new Vector2d(2,0);
        Vector2d vec2=new Vector2d(9,0);
        animals.put(vec1,new Animal(MapDirection.EAST,correctMap1,vec1));
        animals.put(vec2,new Animal(MapDirection.EAST,correctMap1,vec2));
//        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(2,0)));
//        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(9,0)));
        assertTrue(correctMap1.toString().equals(checkMap.toString()));
    }

    @Test
    void complexRouteTest(){
        IWorldMap checkMap=new RectangularMap(10,5);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        IEngine engine1=new SimulationEngine(new OptionsParser().parse(
                new String[] {"f", "b", "r","l","f", "f","r","r","f","f","f","f","f","f","f","f"})
                ,checkMap,positions);
        engine1.run();
        IWorldMap correctMap= new RectangularMap(10,5);
        RectangularMap correctMap1 = (RectangularMap) correctMap;
        Map<Vector2d,Animal> animals = correctMap1.getAnimals();
        Vector2d vec1=new Vector2d(3,5);
        Vector2d vec2=new Vector2d(2,0);
        animals.put(vec1,new Animal(MapDirection.NORTH,correctMap1,vec1));
        animals.put(vec2,new Animal(MapDirection.SOUTH,correctMap1,vec2));
//        animals.add(new Animal(MapDirection.NORTH,correctMap1,new Vector2d(3,5)));
//        animals.add(new Animal(MapDirection.SOUTH,correctMap1,new Vector2d(2,0)));
        System.out.println(correctMap1);
        System.out.println(checkMap);
        assertTrue(correctMap1.toString().equals(checkMap.toString()));
    }

    @Test
    void complexRouteThreeAnimalsTest(){
        IWorldMap checkMap=new RectangularMap(10,5);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4),new Vector2d(5,4)};
        IEngine engine1=new SimulationEngine(new OptionsParser().parse(
                new String[] {"f", "b", "r","l","f", "f","r","r","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f"})
                ,checkMap,positions);
        engine1.run();
        IWorldMap correctMap= new RectangularMap(10,5);
        RectangularMap correctMap1 = (RectangularMap) correctMap;
        Map<Vector2d,Animal> animals = correctMap1.getAnimals();
        Vector2d vec1=new Vector2d(2,5);
        Vector2d vec2=new Vector2d(9,4);
        Vector2d vec3=new Vector2d(10,4);
        animals.put(vec1,new Animal(MapDirection.NORTH,correctMap1,vec1));
        animals.put(vec2,new Animal(MapDirection.EAST,correctMap1,vec2));
        animals.put(vec3,new Animal(MapDirection.EAST,correctMap1,vec3));
//        animals.add(new Animal(MapDirection.NORTH,correctMap1,new Vector2d(2,5)));
//        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(9,4)));
//        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(10,4)));
        assertTrue(correctMap1.toString().equals(checkMap.toString()));
    }

}
