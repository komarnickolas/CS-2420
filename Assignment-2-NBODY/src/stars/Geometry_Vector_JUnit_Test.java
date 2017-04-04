package stars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 1/23/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Geometry_Vector_JUnit_Test {
    private Geometry_Vector testVector;
    @Test
    public void testAdd(){
        testVector = new Geometry_Vector(0,0);
        Geometry_Vector toAdd = new Geometry_Vector(50,50);
        testVector.add_to_me(toAdd);
        assertEquals(50,testVector.getX());
        assertEquals(50,testVector.getY());
        testVector.add_to_me(new Geometry_Vector(10,50));
        assertEquals(60,testVector.getX());
        assertEquals(100,testVector.getY());
    }

    @Test
    public void testSubtract(){
        testVector = new Geometry_Vector(100,100);
        Geometry_Vector toSubtract = new Geometry_Vector(10,10);
        testVector.subtract_from_me(toSubtract);
        assertEquals(90,testVector.getX());
        assertEquals(90,testVector.getY());
        testVector.subtract_from_me(new Geometry_Vector(40,50));
        assertEquals(50,testVector.getX());
        assertEquals(40,testVector.getY());
    }

    @Test
    public void testMultiply(){
        testVector = new Geometry_Vector(5,5);
        testVector.multiply_me_by(10);
        assertEquals(50,testVector.getX());
        assertEquals(50,testVector.getY());
        testVector.multiply_me_by(10);
        assertEquals(500,testVector.getX());
        assertEquals(500,testVector.getY());
    }

    @Test
    public void testDivision(){
        testVector = new Geometry_Vector(100,100);
        testVector.divide_by(10);
        assertEquals(10,testVector.getX());
        assertEquals(10,testVector.getY());
        testVector.divide_by(5);
        assertEquals(2,testVector.getX());
        assertEquals(2,testVector.getY());
    }

    @Test
    public void testMagnitude(){
        testVector = new Geometry_Vector(0,10);
        assertEquals(10,testVector.magnitude());
        testVector.multiply_me_by(10);
        assertEquals(100, testVector.magnitude());
    }

    @Test
    public void testNormalize(){
        testVector = new Geometry_Vector(0,10);
        testVector.normalize();
        assertEquals(1,(int) testVector.magnitude());
        testVector = new Geometry_Vector(50,76);
        testVector.normalize();
        assertEquals(1,(int) testVector.magnitude());
    }
}
