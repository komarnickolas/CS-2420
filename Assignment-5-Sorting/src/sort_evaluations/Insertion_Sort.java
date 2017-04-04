package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 * Performs an insertion sort on array
 * 1. Take and element in array
 * 2. Compare to one behind it
 * 3. If less than swap
 * 4. Repeat until all elements have been compared
 */
public class Insertion_Sort<Type extends Comparable<? super Type>> implements Sorter<Type>// make generic and implement Sorter
{

	/**
	 * Name of the sort
	 */
	public String name_of_sort(){
		return "Insertion Sort";
	}

	/**
	 * No affect on insertion sort
	 */
	public void set_constant( double constant ){
		System.out.println("Ignored");
	}

	/**
	 * Calls insertion sort
	 */
	public void sort( ArrayList<Type> array ){
		if(array.size() < 1){
			return;
		}
		Sort_Utils.insertion_sort(array,0,array.size()-1);
	}

	/**
	 * @return the expected complexity of this algorithm
	 */
	@Override
	public Complexity_Class get_expected_complexity_class(){
		return Complexity_Class.N_squared;
	}


}
