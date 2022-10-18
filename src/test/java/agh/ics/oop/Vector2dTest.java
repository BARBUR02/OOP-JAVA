package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    //Metoda equals

    @Test
    void ShouldEqualTest(){
        Vector2d vector1 = new Vector2d(1,1);
        Vector2d vector2 = new Vector2d(1,1);
        assertTrue(vector1.equals(vector2) && vector2.equals(vector1));
    }

    @Test
    void bothDifferentTest(){
        Vector2d vector1=new Vector2d(-6,5);
        Vector2d vector2=new Vector2d(-5,-4);
        assertFalse(vector1.equals(vector2) || vector2.equals(vector1));
    }

    @Test
    void onlyFirstSameTest(){
        Vector2d vector1=new Vector2d(-5,5);
        Vector2d vector2=new Vector2d(-5,-4);
        assertFalse(vector1.equals(vector2) || vector2.equals(vector1));
    }

    @Test
    void onlySecondSameTest(){
        Vector2d vector1=new Vector2d(-1,7);
        Vector2d vector2=new Vector2d(-5,7);
        assertFalse(vector1.equals(vector2) || vector2.equals(vector1));
    }

    //Metoda toString

    @Test
    void ToStringTest(){
        Vector2d vector = new Vector2d(3,-6);
        assertEquals(vector.toString(),"(3,-6)");
    }


    //Metoda precedes
    @Test
    void precedesTestSmallerTest(){
        Vector2d vector1=new Vector2d(0,0);
        Vector2d vector2=new Vector2d(1,7);
        assertTrue(vector1.precedes(vector2));
    }

    @Test
    void precedesTestEqualTest(){
        Vector2d vector1=new Vector2d(0,0);
        Vector2d vector2=new Vector2d(0,0);
        assertTrue(vector1.precedes(vector2));
    }

    @Test
    void precedesTestEdgyTest(){
        Vector2d vector1=new Vector2d(-1,2);
        Vector2d vector2=new Vector2d(0,0);
        assertFalse(vector1.precedes(vector2));
    }

    //metoda follows
    @Test
    void followsTestBiggerTest(){
        Vector2d vector1=new Vector2d(4,9);
        Vector2d vector2=new Vector2d(1,7);
        assertTrue(vector1.follows(vector2));
    }

    @Test
    void followsTestEqualTest(){
        Vector2d vector1=new Vector2d(0,0);
        Vector2d vector2=new Vector2d(0,0);
        assertTrue(vector1.follows(vector2));
    }

    @Test
    void followsTestEdgyTest(){
        Vector2d vector1=new Vector2d(-1,2);
        Vector2d vector2=new Vector2d(0,1);
        assertFalse(vector1.precedes(vector2));
    }

    //Metoda upper right
    @Test
    void UpperRightTest(){
        Vector2d vector1=new Vector2d(-1,2);
        Vector2d vector2=new Vector2d(0,1);
        assertEquals(new Vector2d(0,2),vector1.upperRight(vector2));
    }

    //Metoda lowerLEFT
    @Test
    void lowerLeftTest(){
        Vector2d vector1=new Vector2d(-1,2);
        Vector2d vector2=new Vector2d(0,1);
        assertEquals(new Vector2d(-1,1),vector1.lowerLeft(vector2));
    }

    //Metoda add
    @Test
    void addTest(){
        Vector2d vector1=new Vector2d(-1,2);
        Vector2d vector2=new Vector2d(0,1);
        assertEquals(new Vector2d(-1,3),vector1.add(vector2));
    }

    //Metoda substract
    @Test
    void subtractTest(){
        Vector2d vector1=new Vector2d(-1,2);
        Vector2d vector2=new Vector2d(0,1);
        assertEquals(new Vector2d(-1,1),vector1.subtract(vector2));
    }

    //Metoda opposite
    @Test
    void oppositeTest(){
        Vector2d vector1=new Vector2d(-1,0);
        assertEquals(new Vector2d(1,0),vector1.opposite());
    }
}
