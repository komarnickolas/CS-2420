package hash_tables;

import java.util.ArrayList;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/1/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Hash_Table_Quadratic_Probing<KeyType, ValueType> extends Hash_Table_Linear_Probing<KeyType,ValueType> {
    /**
     * Hash Table Constructor
     *
     * @param initial_capacity - try to make this equal to twice the expected number of values
     */
    public Hash_Table_Quadratic_Probing(int initial_capacity) {
        super(initial_capacity);
    }

    @Override
    public void nextIndex(){
        current = hash;
        int next = hash + (probe^2);
        probe++;
        for(int i = 0; i<next; i++){
            if(current >= capacity()){
                current = 0;
            }else {
                current++;
            }
        }
    }


    /**
     * Fill in calculations to show some of the stats about the hash table
     */
    @Override
    public String toString()
    {
        String result = new String();
        ArrayList<Double> stats = print_stats();
        result = "------------ Hash Table Quadratic Info ------------\n"
                + "  Average collisions: "+stats.get(0)+"\n"
                + "  Average Hash Function Time: "+stats.get(1)+"\n"
                + "  Average Insertion Time: "+stats.get(2)+"\n"
                + "  Average Find Time: "+stats.get(3)+"\n"
                + "  Percent filled : "+stats.get(4)+"\n"
                + "  Size of Table  : "+capacity()+"\n"
                + "  Elements       : "+size()+"\n"
                + "-----------------------------------------\n";
        return result;

    }

}
