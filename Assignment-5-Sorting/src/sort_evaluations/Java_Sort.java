
package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 *         this code wraps JAVAs arraylist built in sort to see
 *         how it compares with our sorts
 * 
 */
public class Java_Sort<Type extends Comparable<? super Type>> implements Sorter<Type>
{

	/**
	 * sort the array using the arraylist built in sort and natural order comparator
	 */
	public void sort( ArrayList<Type> array ){
		array.sort(null);
	}

	/**
	 * Name of sort
	 */
	public String name_of_sort(){
		return "Java Sort";
	}


	public void set_constant( double constant ){
		System.out.println("Unmodifiable");
	}


	/**
	 * @return the expected complexity for javas sorts
	 */
	@Override
	public Complexity_Class get_expected_complexity_class(){
		return Complexity_Class.N_log_N;
	}
	

}
