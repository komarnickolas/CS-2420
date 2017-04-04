package assignment04;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/3/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class AnagramUtilTests {
    public static AnagramUtil anagramUtil;
    @BeforeClass
    public static void setUp(){
        anagramUtil = new AnagramUtil();
    }
    @Test
    public void testInsertionSort(){
        Integer[] array = {5,6,2,1,4,7};
        Integer[] expected = {1,2,4,5,6,7};
        anagramUtil.insertionSort(array,null);
        assertEquals(expected,array);
        String test = "test";
        String estt = "estt";
        assertEquals(estt, anagramUtil.sort(test));
        String[] stringArray = {"a","d","f","c","g","k"};
        String[] expectedString = {"a","c","d","f","g","k"};
        anagramUtil.insertionSort(stringArray,null);
        assertEquals(expectedString, stringArray);
    }
    @Test
    public void testAreAnagrams(){
        assertTrue(anagramUtil.areAnagrams("Ate", "Eat"));
        assertTrue(anagramUtil.areAnagrams("Ate", "Tea"));
        assertTrue(anagramUtil.areAnagrams("secured", "rescued"));
        assertTrue(anagramUtil.areAnagrams("Caller", "recall"));
        assertFalse(anagramUtil.areAnagrams("Taste", "Test"));
        assertFalse(anagramUtil.areAnagrams("Taste", "Less"));
        assertFalse(anagramUtil.areAnagrams("Java", "Lava"));
        assertFalse(anagramUtil.areAnagrams("supercalifragilisticexpialidocious", "Antidisestablishmentarianism"));
    }
    @Test
    public void testGetLargestAnagrams(){
        int sizeOfGroup = anagramUtil.getLargestAnagramGroup("words").length;
        assertEquals(7,sizeOfGroup);
    }
}
