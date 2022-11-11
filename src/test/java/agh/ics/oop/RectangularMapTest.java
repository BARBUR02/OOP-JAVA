package agh.ics.oop;


import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RectangularMapTest {

    @Test
    void initTest(){
        IWorldMap checkMap=new RectangularMap(10,5);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,3), new Vector2d(2,2),
        new Vector2d(3,3)};
        IEngine engine1=new SimulationEngine(new OptionsParser().parse(new String[] {"fe"}) ,checkMap,positions);
        engine1.run();
        IWorldMap correctMap= new RectangularMap(10,5);
        RectangularMap correctMap1 = (RectangularMap) correctMap;
        List<Animal> animals = correctMap1.getAnimals();
        animals.add(new Animal(correctMap1,new Vector2d(2,2)));
        animals.add(new Animal(correctMap1,new Vector2d(3,3)));
        assertTrue(correctMap1.toString().equals(checkMap.toString()));
    }

    @Test
    void blockTest(){
        IWorldMap checkMap=new RectangularMap(10,5);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,3)};
        IEngine engine1=new SimulationEngine(new OptionsParser().parse(new String[] {"f", "l", "l"," f"}) ,checkMap,positions);
        engine1.run();
        IWorldMap correctMap= new RectangularMap(10,5);
        RectangularMap correctMap1 = (RectangularMap) correctMap;
        List<Animal> animals = correctMap1.getAnimals();
        animals.add(new Animal(MapDirection.WEST,correctMap1,new Vector2d(2,3)));
        animals.add(new Animal(MapDirection.WEST,correctMap1,new Vector2d(3,3)));
        assertTrue(correctMap1.toString().equals(checkMap.toString()));

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
        List<Animal> animals = correctMap1.getAnimals();
        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(2,0)));
        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(9,0)));
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
        List<Animal> animals = correctMap1.getAnimals();
        animals.add(new Animal(MapDirection.NORTH,correctMap1,new Vector2d(3,5)));
        animals.add(new Animal(MapDirection.SOUTH,correctMap1,new Vector2d(2,0)));
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
        List<Animal> animals = correctMap1.getAnimals();
        animals.add(new Animal(MapDirection.NORTH,correctMap1,new Vector2d(2,5)));
        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(9,4)));
        animals.add(new Animal(MapDirection.EAST,correctMap1,new Vector2d(10,4)));
        assertTrue(correctMap1.toString().equals(checkMap.toString()));
    }

}
