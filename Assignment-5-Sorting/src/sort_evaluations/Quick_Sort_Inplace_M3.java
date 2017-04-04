
package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 *  use the median of three technique to compare vs random pivot selection, etc.
 */
public class Quick_Sort_Inplace_M3<Type extends Comparable<? super Type>> extends Quick_Sort<Type>  // FIXME: make quick sort the super type
{

	/**
	 * Median of Three (choose the middle element position)
	 * 
	 * WARNING: this not only chooses the pivot, but modifies the position of the three elements.
	 * 
	 * 1) Choose 3 elements from the array (start, middle, end)
	 * 2) Place the median element at array[end-1]
	 * 3) Place low element at array[start]
	 * 4) Place high element at array[end]
	 * 
	 * You shouldn't call this method when the array is smaller than 3 elements
	 * 
	 * @param array the array with all the data (we sort a sub piece of the array)
	 * @param start  = index of start of array
	 * @param end    = index of end of array
	 */
	protected Type choose_pivot(ArrayList<Type> array, int start, int end )
	{
		//       additionally, make sure to follow the comments above
		int middle = (end+start)/2;
		Type one = array.get(start);
		Type two = array.get(middle);
		Type three = array.get(end);
		if(one.compareTo(two) > 0 && one.compareTo(three) > 0){
			Sorter.swap(array,start,end);
			if(three.compareTo(two) > 0){
				Sorter.swap(array,start,middle);
			}
		}else if(two.compareTo(three) > 0 && two.compareTo(one) > 0){
			Sorter.swap(array, middle,end);
			if(one.compareTo(three) > 0){
				Sorter.swap(array,start,middle);
			}
		}else{
			if(one.compareTo(two) >0){
				Sorter.swap(array,start,middle);
			}
		}

		Sorter.swap(array,middle,end-1);
		Sorter.swap(array,end-1,end);

		return array.get(end);
	}


	/**
	 * Name the sort
	 */
	public String name_of_sort()
	{
		return "Quick Sort Inplace M3(Cutoff: "+cutoff + ")";
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
