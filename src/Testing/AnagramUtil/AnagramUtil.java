package Testing.AnagramUtil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 2/3/2017
 * 2420
 * Assignment 01
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class AnagramUtil {

    public AnagramUtil(){
    }
    /**
     * Performs an insertion sort on an input string
     *
     * @param inputAnagram -- string to sort
     * @return -- sorted string
     */
    public static String sort(String inputAnagram) {
        String sorted = "";
        String[] input = inputAnagram.split("");
        insertionSort(input,new CustomStringComparator());
        for (String string : input) {
            sorted += string;
        }
        return sorted;
    }

    /**
     * Performs an insertion sort on an input array using a custom comparator
     *
     * @param inputArray
     *            -- input array of items
     * @param comparator
     *            -- input custom comparator
     */
    public static <T> void insertionSort(T[] inputArray, Comparator<? super T> comparator) {
        for (int i = 1; i < inputArray.length; i++) {
            T index = inputArray[i];
            int next = i;
            if (comparator != null) {
                while (next > 0 && comparator.compare(inputArray[next - 1], index) > 0) {
                    inputArray[next] = inputArray[next - 1];
                    next--;
                }
            }
            else {
                while (next > 0 && (((Comparable) inputArray[next - 1]).compareTo(index)) > 0) {
                    inputArray[next] = inputArray[next - 1];
                    next--;
                }
            }
            inputArray[next] = index;
        }
    }

    /**
     * Determines if two string inputs are anagrams of one another
     *
     * @param possibleAnagram1
     *            -- String input 1
     * @param possibleAnagram2
     *            -- String input 2
     * @return -- true, if both input strings are anagrams, false otherwise
     */
    public static boolean areAnagrams(String possibleAnagram1, String possibleAnagram2) {
        if (possibleAnagram1.length() != possibleAnagram2.length()) {
            return false;
        }
        String string1 = sort(possibleAnagram1);
        String string2 = sort(possibleAnagram2);
        if (string1.toLowerCase().compareTo(string2.toLowerCase()) != 0) {
            return false;
        }
        return true;

    }

    /**
     *
     * @param string - string to getKey
     * @return - the ascii value of the string as all lower case
     */
    public static String getKey(String string){
        return sort(string.toLowerCase());
    }

    /**
     * Finds the largest group of anagrams in the given array
     *
     * @param anagramsList -- list of anagrams to check
     * @return -an array of the largest anagram group
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String[] getLargestAnagramGroup(String[] anagramsList) {

        // checks if anagramsList is empty, if it is return the list
        if (anagramsList.length <= 1) {
            return anagramsList;
        }
        Arrays.sort(anagramsList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(!areAnagrams(o1,o2)){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        int size_largest = 0, size_current = 0, low_largest = 0, low_current = 0, high_largest = 0, high_current = 0;
        for(int i = 1; i < anagramsList.length; i++){
            if(areAnagrams(anagramsList[i], anagramsList[i-1])){
                size_current++;
            }else{
                if(size_current > size_largest){
                    size_largest = size_current;
                    low_largest = low_current;
                    high_largest = high_current;
                }
                size_current = 0;
                low_current = i;
            }
            high_current = i;
        }
        if(size_current > size_largest){
            size_largest = size_current;
            low_largest = low_current;
            high_largest = high_current;
        }
        ArrayList<String> largest = new ArrayList<>();
        while (low_largest < high_largest){
            largest.add(anagramsList[low_largest]);
            low_largest++;
        }
        return largest.toArray(new String[largest.size()]);
    }

    /**
     * Finds the largest anagram group in the file
     *
     * @param filename -- file to find largest group in
     * @return -- the largest group of anagrams
     */
    public static String[] getLargestAnagramGroup(String filename) {
        return getLargestAnagramGroup(readFile(filename));
    }

    //

    /**
     * Reads words from a file (assumed to contain one word per line)
     * @param filename - file to read
     * @return -- the words as an array of strings
     */
    public static String[] readFile(String filename) {
        ArrayList<String> results = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("Resources/" + filename))) {
            //adds the words on each line to the results array
            stream.forEach(results :: add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results.toArray(new String[results.size()]);
    }


    /**
     *
     * @param length -- number of characters in the word
     * @return -- random word
     */
    public static String randomString(int length) {
        Random rand = new Random();
        char[] string = new char[length];
        for(int i = 0; i < length; i++) {
            // ASCII values a-z,A-Z are contiguous (52 characters)
            int randomChar = (rand.nextInt(122-97) + 97);
            string[i] = (char) randomChar;
        }
        return new String(string);
    }
}
