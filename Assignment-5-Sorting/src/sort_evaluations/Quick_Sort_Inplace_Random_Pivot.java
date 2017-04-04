
package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 * 
 */
public class Quick_Sort_Inplace_Random_Pivot<Type extends Comparable<? super Type>> extends Quick_Sort<Type> // FIXME: extend from Quick Sort class
{

	/**
	 * Random_Pivot
	 * 
	 * 1) Choose an element at random in the array and use it as pivot
	 * 2) Place this element at array[end]
	 * 
	 * @param array     = the array with all the data (we sort a sub piece of the array)
	 * @param start      = index of start of array
	 * @param end        = index of end of array
	 */
	protected Type choose_pivot(ArrayList<Type> array, int start, int end )
	{
		int index = start + ((int) Math.random() * end);
		Sorter.swap(array,index,end);
		return array.get(end);
	}

	/**
	 * Name the sort
	 */
	public String name_of_sort()
	{
		return "Quick Sort Inplace Random Pivot (Cutoff: "+cutoff+")";
	}

	/**
	 *
	 * @return expected BIG O behavior
	 */
	@Override
	public Complexity_Class get_expected_complexity_class() {
		return Complexity_Class.N_log_N;
	}


}
