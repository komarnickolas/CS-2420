/**
 * 
 */
package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 * Pseudocode:
 *     built a list of sorters
 *     add all of the sorts (e.g., Quick_Sort_Naive, Quick_Sort_Inplace...) to the list
 *     for each element of the list
 *        time it for a wide range of values (e.g., 10,000 -> 100,000,000)
 *     test insertion sort separately because you can't wait that long...
 *
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		ArrayList<Sorter<Integer>> sort_methods = new ArrayList<>();
		sort_methods.add(new Java_Sort<>());
		sort_methods.add(new Merge_Sort<>());
		Sort_Utils sort_utils = new Sort_Utils();
		for(Sorter<Integer> sorter : sort_methods){
			sort_utils.test_and_time(sorter,0,10,100,Integer.MAX_VALUE);
		}
		// ...
		
	}

}
