package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private Animal animal;

    // checked object init
    @BeforeEach
    void init(){
        IWorldMap map=new RectangularMap(4,4);
        animal=new Animal(map);
        map.place(animal);
    }

    // init check
    @Test
    void correctInitTest(){
        assertTrue(animal.isAt(new Vector2d(2,2)) && MapDirection.NORTH==animal.getOrient());
    }

    // input data behaviour
    @Test
    void noValidMovesTest(){
        String[] args={"bluh","1","F","R","backwards","Forwards","121saf."};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(2,2)) && MapDirection.NORTH==animal.getOrient());
    }

    @Test
    void noArgumentsTest(){
        String[] args={};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(2,2)) && MapDirection.NORTH==animal.getOrient());
    }

    // orientation check
    @Test
    void orientationTest(){
        String[] args={"f","b","f","r","f","f","r","f","r","l","r"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getOrient()==MapDirection.WEST);
    }


    //Border Edges Tests
    @Test
    void NorthEdgeTest(){
        String[] args={"f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
        && animal.getPosition().follows(new Vector2d(0,0)));
    }

    @Test
    void EastEdgeTest(){
        String[] args={"r","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
                && animal.getPosition().follows(new Vector2d(0,0)));
    }

    @Test
    void WestEdgeTest(){
        String[] args={"l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
                && animal.getPosition().follows(new Vector2d(0,0)));
    }

    @Test
    void SouthEdgeTest(){
        String[] args={"l","l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
                && animal.getPosition().follows(new Vector2d(0,0)));
    }

    @Test
    void closedPathTest(){
        String[] args={"f","f","r","f","f","r","f","f","r","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(2,2)));
    }

    @Test
    void closedPathMixedValuesTest(){
        String[] args={"f","f","akdag","FORWARDS","backwards","r","","f","f","r","f","f","fsafsaf","!!!","r","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(2,2)));
    }

    @Test
    void simpleRouteTest(){
        String[] args={"f","b","f","r","f","f","r","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,2)) && animal.getOrient()==MapDirection.SOUTH);
    }


    @Test
    void ComplexRouteOneTest(){
        String[] args={"f","r","r","f","f","f","f","l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
    }

    @Test
    void ComplexRouteOneMixedValuesTest(){
        String[] args={"f","r","r","","f","fsafsa","f","f","f","Forward","l","BACKWARD","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
    }

    @Test
    void ComplexRouteTwoTest(){
        String[] args={"f","r","r","f","f","f","f","l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
    }

    @Test
    void ComplexRouteTwoMixedValuesTest(){
        String[] args={"f","r","fafsa","r","f","","f","f","f","l","f","f","Forward","f","f","F","R"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
    }

    @Test
    void ComplexRouteThreeTest(){
        String[] args={"f","r","r","r","f","f","f","f","right","f","r","f","f","l","l","forward"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(1,4)) && animal.getOrient()==MapDirection.WEST);
    }

    @Test
    void ComplexRouteThreeMixedValuesTest(){
        String[] args={"forwards","backwarD","f","r","r","ahfhas","r","","f","f","f","f","right","f","r","f","f","l","l","forward"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(1,4)) && animal.getOrient()==MapDirection.WEST);
    }



}
