package assignment04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Elijah Yarisantos
 * Nickolas Komarnitsky
 */
public class AnagramUtil
{
    /**
     * This method takes in a string and returns it as a sorted words in lexigraphical order
     * and combines it with rest of the sorted words
     * @param list
     * @return
     */
    public static String sort(String list)
    {
        String [] array  = list.toLowerCase().split("");
        insertionSort(array, null);
        String sorted_string = "";
        for(String string : array)
        {
            sorted_string += string;
        }

        return sorted_string;
    }


    /**
     * This Genric methods uses insertionsort. it compares the current index with the indices behind it to check if
     * it is anagram, after the sort is done then all anagrams should be next to each other.
     * @param input_Array
     * @param compare
     */
    public static <T> void insertionSort(T[] input_Array, Comparator<? super T> compare)
    {
        for(int index = 1; index < input_Array.length; index++)
        {
            int variable_to_compare = index;
            T current = input_Array[index];
            if(compare != null )
            {
                while( variable_to_compare > 0 && compare.compare(input_Array[variable_to_compare - 1], current)> 0)
                {
                    input_Array[variable_to_compare] = input_Array[variable_to_compare - 1];
                    variable_to_compare--;
                }
            }
            else
            {
                while(variable_to_compare > 0 && (((Comparable)input_Array[variable_to_compare - 1]).compareTo(current))> 0)
                {
                    input_Array[variable_to_compare] = input_Array[variable_to_compare - 1];
                    variable_to_compare--;
                }
            }
            input_Array[variable_to_compare] = current;
        }
    }

    /**
     * This method checks to see if the two string are anagrams, returns true if they are
     * returns false if they are not
     * @param string_1
     * @param string_2
     * @return
     */
    public static boolean areAnagrams(String string_1, String string_2 )
    {
        if(string_1.length() != string_2.length())
        {
            return false;
        }

        String sorted_string1 = sort(string_1);
        String sorted_string2 = sort(string_2);

        if(sorted_string1.compareTo(sorted_string2) == 0)
        {
            return true;
        }

        return false;
    }

    /**
     * First checks to see if the array is empty or has 1 index
     * It uses insertion sort to check if the two strings next to each other are anagrams
     * If they are then we keep track of the how many anagrams their are by keeping tabs on the index from the low to high
     *
     * the group with the biggest size is then returned as largest anagram
     *
     * @param anagram_input
     * @return
     */
    public static String[] getLargestAnagramGroup(String[] anagram_input)
    {
        //returns an empty array if thier is no anagrams
        if(anagram_input.length <= 1)
        {
            return anagram_input;
        }

        //sort the list that is given first
        insertionSort(anagram_input, new Comparator<String>() {
            @Override
            //if they are anagrams return 0 if not return 1
            public int compare(String s1, String s2)
            {
                if(!areAnagrams(s1, s2))
                {
                    return 1;
                }
                else
                {
                    return 0;
                }

            }});
        System.out.println();

        int size_largest = 0, size_current = 0;
        int low_largest = 0, high_largest = 0, low_current = 0, high_current = 0;
        //
        for(int index = 1; index < anagram_input.length; index++)
        {
            if(areAnagrams(anagram_input[index], anagram_input[index-1]))
            {
                high_current = index;
                size_current++;
            }
            else
            {
                if(size_current > size_largest)
                {
                    size_largest = size_current;
                    low_largest = low_current;
                    high_largest = high_current;
                }
                low_current = index;
                size_current = 0;
            }
        }

        if(size_current > size_largest)
        {
            size_largest = size_current;
            low_largest = low_current;
            high_largest = high_current;
        }

        String[] biggest_group = new String [size_largest+1];
        for(int index = 0; index < biggest_group.length; index++)
        {
            biggest_group[index] = anagram_input[low_largest];
            low_largest++;
        }

        return biggest_group;

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
    public static String[] getLargestAnagramGroupHashMap(String[] anagramsList){

        // checks if anagramsList is empty, if it is return the list
        if (anagramsList.length <= 1) {
            return anagramsList;
        }

        HashMap<String, ArrayList<String>> anagrams = new HashMap<>();

        for(String anagram : anagramsList){
            String key = getKey(anagram);
            if(!anagrams.containsKey(key)){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(anagram);
                anagrams.put(key,temp);
            }else{
                anagrams.get(key).add(anagram);
            }
        }
        ArrayList<String> largest = new ArrayList<>();
        for(String key : anagrams.keySet()){
            if(anagrams.get(key).size() > largest.size()){
                largest = anagrams.get(key);
            }
        }
        return largest.toArray(new String[largest.size()]);
    }

    /**
     * Behaves the same as the previous method, but reads the list of words from the input filename.
     * It is assumed that the file contains one word per line.
     * If the file does not exist or is empty, the method returns an empty array because there are no anagrams.
     * @param file
     * @return
     */
    public static String[] getLargestAnagramGroup(String file)
    {
        String [] empty = {};

        if(convert_file_String(file).length == 0)
        {
            return empty;
        }

        return getLargestAnagramGroup(convert_file_String(file));
    }
    /**
     * Behaves the same as the previous method, but reads the list of words from the input filename.
     * It is assumed that the file contains one word per line.
     * If the file does not exist or is empty, the method returns an empty array because there are no anagrams.
     * @param file
     * @return
     */
    public static String[] getLargestAnagramGroupHashMap(String file)
    {
        String [] empty = {};

        if(convert_file_String(file).length == 0)
        {
            return empty;
        }

        return getLargestAnagramGroupHashMap(convert_file_String(file));
    }

    /**
     * This method takes in a string of the file name and converts it to a
     * String [] array.
     * @param file_anagram
     * @return
     */
    public static String[] convert_file_String(String file_anagram)
    {
        ArrayList<String> results = new ArrayList<String>();
        try(Stream<String> stream = Files.lines(Paths.get("Resources/"+ file_anagram )))
        {
            stream.forEach(results :: add);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        return results.toArray(new String[results.size()]);
    }

}