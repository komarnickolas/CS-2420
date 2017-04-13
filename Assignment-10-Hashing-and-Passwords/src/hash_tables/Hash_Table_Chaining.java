package hash_tables;

import java.util.ArrayList;
import java.util.LinkedList;

import static hash_tables.Primes.next_prime;

/**
 * Nickolas Komarnitsky
 * u0717854
 * 4/1/2017
 * 2420
 * Assignment 06
 * I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. (Name)
 * Nickolas Komarnitsky
 */
public class Hash_Table_Chaining<KeyType, ValueType> implements Hash_Map<KeyType,ValueType> {

    private ArrayList<LinkedList<Pair<KeyType, ValueType>>> table;
    protected int								capacity;
    protected int								num_of_entries;
    protected boolean                           resize;
    private long 								total_find_time, total_insertion_time, total_hash_function_time;
    private int								    find_times, insertion_times, hash_function_times, collisions;

    public Hash_Table_Chaining(int initial_capacity){
        table = new ArrayList<>();
        this.capacity = next_prime(initial_capacity);
        init_table();
    }

    @Override
    public void insert(KeyType key, ValueType value) {
        long insert_start = System.nanoTime();
        if(size() > capacity()/2 && resize){
            resize(capacity() *2);
        }
        int index = hash(key);
        if (table.get(index) == null) {
            table.set(index,new LinkedList<>());
            table.get(index).add(new Pair<>(key,value));
            num_of_entries++;
        } else {
            collisions++;
            table.get(index).add(new Pair<>(key,value));
        }
        long insert_end = System.nanoTime() - insert_start;
        total_insertion_time += insert_end;
        insertion_times++;
    }

    @Override
    public ValueType find(KeyType key) {
        long find_start = System.nanoTime();
        int index = hash(key);
        if(table.get(index) != null){
            ValueType value = null;
            for(Pair pair : table.get(index)){
                collisions++;
                if(pair.key.equals(key)){
                    value = (ValueType) pair.value;
                }
            }
            long find_end = System.nanoTime()-find_start;
            total_find_time += find_end;
            find_times++;
            return value;
        }
        long find_end = System.nanoTime()-find_start;
        total_find_time += find_end;
        find_times++;
        return null;
    }


    private int hash(KeyType key){
        long start = System.nanoTime();
        int hash = Math.abs(key.hashCode() % capacity());
        long end = System.nanoTime() - start;
        total_hash_function_time += end;
        hash_function_times++;
        return hash;
    }


    @Override
    public void clear() {
        init_table();
        this.num_of_entries = 0;
        reset_stats();
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return num_of_entries;
    }

    @Override
    public void set_resize_allowable(boolean status) {
        resize = status;
    }

    /**
     * Clear the hash table array and initializes all of the entries in the table to null.
     *
     *
     */
    private void init_table()
    {
        // FIXME:
        // 1) build an array list of CAPACITY null values
        //       note 1: create an initial array list (which will have a size 0) but set the capacity to CAPACITY
        //       note 2: then, you must explicitly insert nulls into the array list CAPACITY times
        // 2) set the number of elements in the hash table to 0
        table = new ArrayList<>(capacity);
        for(int i = 0; i<=capacity; i++){
            table.add(null);
        }
        num_of_entries = 0;
    }

    /**
     * Resets the hash table stats.
     *
     */
    @Override
    public void reset_stats()
    {
        total_hash_function_time = 0;
        hash_function_times = 0;
        total_find_time = 0;
        find_times=0;
        total_insertion_time = 0;
        insertion_times = 0;
        collisions = 0;
    }
    /**
     *
     */
    public ArrayList<Double> print_stats()
    {
        ArrayList<Double> stats = new ArrayList<>();

        double avg_collision = collisions;
        avg_collision/=(insertion_times + find_times);
        stats.add(avg_collision);

        double avg_hash_function = total_hash_function_time;
        avg_hash_function/=hash_function_times;
        stats.add(avg_hash_function);

        double avg_insertion = total_insertion_time;
        avg_insertion/=insertion_times;
        stats.add(avg_insertion);

        double avg_find = total_find_time;
        avg_find/=find_times;
        stats.add(avg_find);

        double percent = size();
        percent/=capacity();
        percent*=100;
        stats.add(percent);

        return stats;
    }

    /**
     * Fill in calculations to show some of the stats about the hash table
     */
    public String toString()
    {
        String result = new String();
        ArrayList<Double> stats = print_stats();
        result = "------------ Hash Table Chaining Info ------------\n"
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

    @Override
    public void resize(int new_size) {
        if(new_size > size()){
            capacity = next_prime(new_size);
            ArrayList<LinkedList<Pair<KeyType, ValueType>>> temp = new ArrayList<>(capacity());
            for(int i = 0; i<capacity(); i++){
                temp.add(null);
            }
            for(int i = 0; i< table.size(); i++){
                temp.set(i, table.get(i));
            }
            table = temp;
        }
    }
}
