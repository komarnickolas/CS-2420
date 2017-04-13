package hash_tables;

import java.util.ArrayList;

import static hash_tables.Primes.next_prime;

/**
 * @author H. James de St. Germain, April 2007
 *         # Adapted by Erin Parker to accept generic items for keys and values
 *
 *         Represents a hash table of key-value pairs.
 *         Linear probing is used to handle conflicts.
 * 
 * 
 *         FIXME: You are to comment all functions and variables
 */
public class Hash_Table_Linear_Probing<KeyType, ValueType> implements Hash_Map<KeyType, ValueType>
{

	/** comment me */
	private ArrayList<Pair<KeyType, ValueType>>	table;
	protected int								capacity;
	protected int								num_of_entries;
	protected boolean 							resize;
	protected long 								total_find_time, total_insertion_time, total_hash_function_time;
	protected int								find_times, insertion_times, hash_function_times, collisions;
	protected int								probe, hash, current;

	/**
	 * Hash Table Constructor
	 * @param initial_capacity - try to make this equal to twice the expected number of values
	 */
	public Hash_Table_Linear_Probing( int initial_capacity )
	{
		this.capacity = next_prime(initial_capacity);
		init_table();
		this.num_of_entries = 0;
		this.find_times = 0;
		this.insertion_times = 0;
		this.hash_function_times = 0;
		this.collisions = 0;
	}

	/**
	 * Puts the given "value" into the hash table under the given "key".
	 * On a duplicate entry, replace the old data with the new "value".
	 * 
	 * For Probing Tables: This method will double* the size of the table if the number
	 *                     of elements is > 1/2 the capacity
	 * For Chaining Tables: double* the size of the table if the average number of collisions
	 *                      is greater than 5.
	 *           *double --> double then choose next greatest prime 
	 * 
	 * FIXME: Make sure you collect statistics in this method. See print_stats().
	 */
	public void insert( KeyType key, ValueType value ) {
		long insert_start = System.nanoTime();
		if(size() > capacity/2 && resize){
			resize(capacity()*2);
		}
		hash = hash(key);
		if(table.get(hash) == null){
			table.set(hash, new Pair<>(key,value));
			num_of_entries++;
		}else if(table.get(hash).key == key) {
			table.get(hash).value = value;
		}else{
			collisions++;
			probe = 2;
			current = hash + 1;
			while(true){
				if(table.get(current) == null){
					table.set(current,new Pair<>(key,value));
					num_of_entries++;
					break;
				}else{
					nextIndex();
					collisions++;
				}
			}
		}
		long insert_end = System.nanoTime() - insert_start;
		total_insertion_time += insert_end;
		insertion_times++;
	}

	private int hash(KeyType key){
		long start = System.nanoTime();
		int hash = Math.abs(key.hashCode() % capacity());
		long end = System.nanoTime() - start;
		total_hash_function_time += end;
		hash_function_times++;
		return hash;
	}


	public void nextIndex(){
		int next = hash + probe;
		probe++;
		if(next >= capacity()){
			int diff = capacity() - hash;
			current = 0 + (probe - diff);
		}else{
			current = next;
		}
	}

	/**
	 * Search for an item in the hash table, using the given "key".
	 * Return the item if it exists in the hash table.
	 * Otherwise, returns null.
	 * 
	 * FIXME: Make sure you collect statistics in this method. See print_stats().
	 */
	public ValueType find( KeyType key )
	{
		// FIXME
		long find_start = System.nanoTime();
		int hash = hash(key);
		if(table.get(hash) != null) {
			if (table.get(hash).key.equals(key)) {
				long find_end = System.nanoTime() - find_start;
				total_find_time += find_end;
				find_times++;
				return table.get(hash).value;
			}else{
				collisions++;
				probe = 2;
				current = hash + 1;
				while(current != hash){
					if(table.get(current) == null){
						return null;
					}else if(table.get(current).key.equals(key)) {
						return table.get(current).value;
					}else {
						nextIndex();
						collisions++;
					}
				}
			}
		}else{
			collisions++;
		}
		long find_end = System.nanoTime() - find_start;
		total_find_time += find_end;
		find_times++;
		return null;
	}

	/**
	 * Remove all items from the hash table (and resets stats).
	 */
	public void clear()
	{
		init_table();
		this.num_of_entries = 0;
		reset_stats();
	}

	/**
	 * Returns the capacity of the hash table.
	 */
	public int capacity()
	{
		// FIXME: return the number of total buckets
		return capacity;
	}

	/**
	 * Returns the number of entries in the hash table (i.e., the number of stored key-value pairs).
	 */
	public int size()
	{
		// FIXME: return the number of filled buckets
		return num_of_entries;
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
		result = "------------ Hash Table Linear Info ------------\n"
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
	 * 
	 */
	public void set_resize_allowable( boolean status )
	{
		resize = status;
	}
	
	/**
	 * Expand the hash table to the new size, IF the new_size is GREATER than the current size
	 * (if not, doesn't do anything)
	 * 
	 * NOTE: The new hash table should have buckets equal in number the next prime number
	 * greater than or equal to the given "new_size". All the data in the original hash
	 * table must be maintained in the recreated hash table.
	 * 
	 * Note: make sure if you change the size, you rebuild your statistics...
	 */
	public void resize( int new_size )
	{
		// FIXME: write code
		if(new_size > size()){
			capacity = next_prime(new_size);
			ArrayList<Pair<KeyType, ValueType>> temp = new ArrayList<>(capacity());
			for(int i = 0; i<capacity(); i++){
				temp.add(null);
			}
			for(int i = 0; i<table.size(); i++){
				temp.set(i,table.get(i));
			}
			table = temp;
		}
	}
	
	


}