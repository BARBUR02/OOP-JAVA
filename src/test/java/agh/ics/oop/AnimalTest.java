package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private Animal animal;
    private IWorldMap map;
    // checked object init
    @BeforeEach
    void init(){
        map=new RectangularMap(4,4);
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
        try {
            String[] args = {"bluh", "1", "F", "R", "backwards", "Forwards", "121saf."};
            MoveDirection[] tasks = OptionsParser.parse(args);
            for (MoveDirection task : tasks) animal.move(task);
            assertTrue(animal.isAt(new Vector2d(2, 2)) && MapDirection.NORTH == animal.getOrient());
        }catch ( Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
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
        try{
        String[] args={"f","b","f","r","f","f","r","f","r","l","r"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getOrient()==MapDirection.WEST);
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }


    //Border Edges Tests
    @Test
    void NorthEdgeTest(){
        try{
        String[] args={"f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
        && animal.getPosition().follows(new Vector2d(0,0)));
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }

    @Test
    void EastEdgeTest(){
        try{
        String[] args={"r","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
                && animal.getPosition().follows(new Vector2d(0,0)));
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }

    @Test
    void WestEdgeTest(){
        try{
        String[] args={"l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
                && animal.getPosition().follows(new Vector2d(0,0)));
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }

    @Test
    void SouthEdgeTest(){
        try{
        String[] args={"l","l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.getPosition().precedes(new Vector2d(4,4))
                && animal.getPosition().follows(new Vector2d(0,0)));
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }

    @Test
    void closedPathTest(){
        try{
        String[] args={"f","f","r","f","f","r","f","f","r","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        System.out.println(animal.getPosition());
        System.out.println(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(animal.isAt(new Vector2d(2,2)));
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }

    @Test
    void closedPathMixedValuesTest(){
        try{
        String[] args={"f","f","akdag","FORWARDS","backwards","r","","f","f","r","f","f","fsafsaf","!!!","r","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(2,2)));
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }

    @Test
    void simpleRouteTest(){
        try{
        String[] args={"f","b","f","r","f","f","r","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,2)) && animal.getOrient()==MapDirection.SOUTH);
        }catch ( Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
        }


    @Test
    void ComplexRouteOneTest(){
        try{
        String[] args={"f","r","r","f","f","f","f","l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
        }catch ( Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
        }

    @Test
    void ComplexRouteOneMixedValuesTest(){
        try{
        String[] args={"f","r","r","","f","fsafsa","f","f","f","Forward","l","BACKWARD","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
    }catch ( Exception e){
        assertTrue(e instanceof IllegalArgumentException);
    }
    }

    @Test
    void ComplexRouteTwoTest(){
        try{
        String[] args={"f","r","r","f","f","f","f","l","f","f","f","f"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
        }catch ( Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
        }

    @Test
    void ComplexRouteTwoMixedValuesTest(){
        try{
        String[] args={"f","r","fafsa","r","f","","f","f","f","l","f","f","Forward","f","f","F","R"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(4,0)) && animal.getOrient()==MapDirection.EAST);
        }catch ( Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
        }

    @Test
    void ComplexRouteThreeTest(){
        try{
        String[] args={"f","r","r","r","f","f","f","f","right","f","r","f","f","l","l","forward"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(1,4)) && animal.getOrient()==MapDirection.WEST);
        }catch ( Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
        }

    @Test
    void ComplexRouteThreeMixedValuesTest(){
        try{
        String[] args={"forwards","backwarD","f","r","r","ahfhas","r","","f","f","f","f","right","f","r","f","f","l","l","forward"};
        MoveDirection[] tasks=OptionsParser.parse(args);
        for (MoveDirection task : tasks) animal.move(task);
        assertTrue(animal.isAt(new Vector2d(1,4)) && animal.getOrient()==MapDirection.WEST);
        }catch ( Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
        }



}
