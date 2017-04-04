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
public class Hash_Table_Quadratic_Probing<KeyType, ValueType> extends Hash_Table_Linear_Probing<KeyType, ValueType> {
    /**
     * Hash Table Constructor
     *
     * @param initial_capacity - try to make this equal to twice the expected number of values
     */
    public Hash_Table_Quadratic_Probing(int initial_capacity, HashFunctor<KeyType> hashFunctor) {
        super(initial_capacity, hashFunctor);
    }
}
