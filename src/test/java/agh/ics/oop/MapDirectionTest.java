package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    void nextNorthTest(){
        MapDirection direction=MapDirection.NORTH;
        assertEquals(direction.next(),MapDirection.EAST);
    }

    @Test
    void nextEastTest(){
        MapDirection direction=MapDirection.EAST;
        assertEquals(direction.next(),MapDirection.SOUTH);
    }

    @Test
    void nextSouthTest(){
        MapDirection direction=MapDirection.SOUTH;
        assertEquals(direction.next(),MapDirection.WEST);
    }

    @Test
    void nextWestTest(){
        MapDirection direction=MapDirection.WEST;
        assertEquals(direction.next(),MapDirection.NORTH);
    }

    @Test
    void previousNorthTest(){
        MapDirection direction=MapDirection.NORTH;
        assertEquals(direction.previous(),MapDirection.WEST);
    }

    @Test
    void previousEastTest(){
        MapDirection direction=MapDirection.EAST;
        assertEquals(direction.previous(),MapDirection.NORTH);
    }

    @Test
    void previousSouthTest(){
        MapDirection direction=MapDirection.SOUTH;
        assertEquals(direction.previous(),MapDirection.EAST);
    }

    @Test
    void previousWestTest(){
        MapDirection direction=MapDirection.WEST;
        assertEquals(direction.previous(),MapDirection.SOUTH);
    }

}
