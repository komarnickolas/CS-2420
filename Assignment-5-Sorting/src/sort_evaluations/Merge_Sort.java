
package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 * @date Spring 2017
 * 
 *       regular merge sort
 *       1. Check for array of size 0 or 1
 *       2. Divide array in half
 *       3. Recursive call on merge sort for first half
 *       4. Recursive call on merge sort for second half
 *       5. Combine
 */
public class Merge_Sort<Type extends Comparable<? super Type>> implements Sorter<Type>// implement sorter
{


	private double threshold = 5;

	/**
	 * @return Name of Sort
	 */
	public String name_of_sort(){
		return "Merge Sort(Cutoff: "+threshold+")";
	}

	/**
	 * Merge Sort
	 * 
	 * split array in half
	 * sort left
	 * sort right
	 * combine
	 * 
	 * 
	 * @param array          the values to sort from small to high
	 * @param low            the index of the starting value in the "virtual array"
	 * @param high           the index of the ending value in the "virtual array"
	 * 
	 */
	private void merge_sort(ArrayList<Type> array, ArrayList<Type> auxillary, int low, int high ){
		int middle = (low + high) / 2;
		boolean cutoff = (middle - low) <= threshold && (high - (middle+1)) <= threshold;
		if((middle - low) <= threshold) {
			Sort_Utils.insertion_sort(array, low, middle);
		}
		if((high - (middle+1)) <= threshold){
			Sort_Utils.insertion_sort(array,middle,high);
		}
		if(low < high && !cutoff) {
			merge_sort(array, auxillary, low, middle);
			merge_sort(array, auxillary, middle + 1, high);
			combine(array, auxillary, low, middle, high);
		}
	}

	/**
	 * combine the values in array into the auxiliary
	 * 
	 * @param array           - original values (the entire array)
	 * @param auxillary       - spare space 
	 * @param low             - low,mid are the lower array
	 * @param mid             - mid,high are the upper array
	 * @param high
	 * 
	 * combine the sub arrays in the _array_ parameter. use the _auxillary_ parameter for scratch space
	 */

	public void combine( ArrayList<Type> array, ArrayList<Type> auxillary, int low, int mid, int high ){
		int cap = high - low +1;
		int index1 = low;
		int index2 = mid + 1;
		int index3 = 0;
		while (index1 <= mid && index2 <= high) {
			if (array.get(index1).compareTo(array.get(index2)) <= 0) {
				auxillary.set(index3, array.get(index1));
				index1++;
			}
			else {
				auxillary.set(index3, array.get(index2));
				index2++;
			}
			index3++;
		}

		while (index1 <= mid) {
			auxillary.set(index3, array.get(index1));
			index1++;
			index3++;
		}
		while (index2 <= high) {
			auxillary.set(index3, array.get(index2));
			index2++;
			index3++;
		}

		for (index3 = 0; index3 < cap; index3++) {
			array.set(low + index3, auxillary.get(index3));
		}
	}

	/**
	 * Allow the insertion sort cut off to be changed
	 */
	public void set_constant( double cutoff ){
		this.threshold = cutoff;
	}

	/**
	 * sort the array
	 */
	@Override
	public void sort( ArrayList<Type> array ) {
		if(array.size() <=1){
			return;
		}
		// Build the auxiliary arrays
		ArrayList<Type> auxillary = new ArrayList<>();
		for(int i = 0; i<array.size(); i++){
			auxillary.add(null);
		}
		// call mergesort
		merge_sort(array,auxillary,0,array.size()-1);
	}

	@Override
	public Complexity_Class get_expected_complexity_class(){
		return Complexity_Class.N_log_N;
	}

}
