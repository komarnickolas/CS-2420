package sort_evaluations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Nickolas Komarnitsky and Jessica Murdock
 * Methods for generating test data, testing sortedness, timing, etc.
 * 
 * 
 * 
 */
public class Sort_Utils
{

	private long start_time, end_time;
	public Label current_sort_label;
	public TextArea array_unsorted;
	public TextArea array_sorted;
	public ObservableList<Timing_Data> data = FXCollections.observableArrayList();

	/**
	 * test to make sure the array is sorted from smallest to largest
	 */
	private static boolean is_sorted( ArrayList<Integer> array )
	{
		boolean sorted = true;
		for(int i = 0; i<array.size()-1; i++){
			if(array.get(i) > array.get(i+1)){
				sorted = false;
				break;
			}
		}
		return sorted;
	}

	/**
	 * test to (kindof) make sure two arrays contain the same values
	 */
	private static boolean sum_equals( ArrayList<Integer> array1, ArrayList<Integer> array2 )
	{
		//        this is an approximation to the question: do the two arrays contain the same
		//        elements.  This is used when you are concerned your sort may be "clobbering" data
		Integer sum1 = 0;
		Integer sum2 = 0;
		for(Integer i : array1){
			sum1 += i;
		}
		for(Integer i : array1){
			sum2 += i;
		}
		return sum1 == sum2;
	}

	
	/**
	 * generate an array of 'size' which is already sorted
	 */
	private static ArrayList<Integer> generate_sorted_array( int size )
	{
		// the first value in the array should be 0, and each value after that
		// one greater.
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0; i<size; i++){
			temp.add(i);
		}
		return temp;
	}

	/**
	 * generate an array of 'size' which is in reverse order largest to smallest
	 */
	private static ArrayList<Integer> generate_reverse_sorted_array( int size )
	{
		//        to store reverse sorted values.
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = size; i>0; i--){
			temp.add(i);
		}
		return temp;
	}

	/**
	 * generate an array of 'size' that has random elements with values from 0 to max
	 */
	public static ArrayList<Integer> generate_random_array( int size, int max )
	{
		Random random = new Random();
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0; i<size; i++){
			temp.add(random.nextInt(max));
		}
		return temp;
	}

	/**
	 * generate an array of 'size' that has all the same element
	 */
	private static ArrayList<Integer> generate_same_array( int size, int element )
	{
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0 ;i<size; i++){
			temp.add(element);
		}
		return temp;
	}

	/**
	 * This function will call the implemented sorted routines
	 * and time them.  It will check already sorted arrays,
	 * reverse sorted arrays, random value arrays, and the same value arrays
	 *
	 * 
	 * @param sort_routine - the routine to test
	 * @param start_count  - how many elements to put in the array
	 * @param count_increment - how many elments to go up for each iteration
	 * @param max_count - when to stop testing
	 * @param timeout_threshold_seconds - if the last iteration of the test took more time than this number, don't do any more tests in this category.
	 *             
	 * Note: the timeout_threshold_seconds probably won't come into consideration until you start timing really big array sets 
	 */
	public void test_and_time(Sorter<Integer> sort_routine,int start_count,int count_increment,int max_count, int timeout_threshold_seconds )
	{
		current_sort_label = new Label("Sorting Using: " + sort_routine.name_of_sort());
//		System.out.printf("Sorting Using %s:\n", sort_routine.name_of_sort());
//		System.out.printf("Time Analysis for \n");
//		System.out.printf("\t\t\t\t\t\tRandom * 10e6 vs.\tWorst Case * 10e6 vs. \n");
//		System.out.printf("   count\tsorted\treverse\trandom\tsame\t%10s\t%10s\n",
//				sort_routine.get_expected_complexity_class().toString(),
//				sort_routine.get_expected_complexity_class().toString());
//		System.out.flush();

		double time_sorted = -1;
		double time_reverse_sorted = -1;
		double time_random = -1;
		double time_all_same = -1;

		boolean do_sorted = true;
		boolean do_reverse = true;
		boolean do_random = true;
		boolean do_same = true;

		for (int count = start_count; count <= max_count; count += count_increment)
		{

			ArrayList<Integer> test = null;
			String arrays = "";
			String arrays_sorted = "";

			///////////////////////////////////////////////////
			// TEST All Same
			if (do_same)
			{

				try
				{
					test = generate_same_array(count, count);
					arrays += "Same:"+test.toString() +"\n";

					timer_on();
					sort_routine.sort(test);
					timer_off();
					time_all_same = get_time();
					arrays_sorted += "Same:"+ test.toString() +"\n";

					if (!is_sorted(test))
					{
						System.out.printf("Error encountered. Array not sorted!\n");
						return;
					}

					if (time_all_same > timeout_threshold_seconds)
					{
						System.out.println("Sorting all same array taking too long");
						do_same = false;
						time_all_same = -1;
					}

				}
				catch (java.lang.OutOfMemoryError error)
				{
					System.out.println(" Sorting the same numbers caused an out of memory error");
					do_same = false;
					time_all_same = -1;
				}
				catch (java.lang.StackOverflowError error)
				{
					System.out.println(
							" Stack Overflow - Probably sorting the same numbers caused an stack overflow error");
					do_same = false;
					time_all_same = -1;
				}
			}

			///////////////////////////////////////////////////
			// TEST RANDOM
			if(do_random) {
				try{
				test = generate_random_array(count, count);
					arrays += "Random:" + test.toString() +"\n";
					timer_on();
					sort_routine.sort(test);
					timer_off();
					time_random = get_time();
					arrays_sorted += "Random:" + test.toString() +"\n";

					if (!is_sorted(test))
					{
						System.out.printf("Error encountered. Array not sorted!\n");
						return;
					}

					if (time_random > timeout_threshold_seconds)
					{
						System.out.println("Sorting all random array taking too long");
						do_random = false;
						time_random = -1;
					}

				}
				catch (java.lang.OutOfMemoryError error)
				{
					System.out.println(" Sorting the random numbers caused an out of memory error");
					do_random = false;
					time_random = -1;
				}
				catch (java.lang.StackOverflowError error)
				{
					System.out.println(
							" Stack Overflow - Probably sorting the random numbers caused an stack overflow error");
					do_random = false;
					time_random = -1;
				}
			}
			/////////////////////////////////////////////////////////
			// TEST already SORTED
			if(do_sorted) {
				try{
					test = generate_sorted_array(count);
					arrays += "Sorted:" + test.toString() +"\n";
					timer_on();
					sort_routine.sort(test);
					timer_off();
					time_sorted = get_time();
					arrays_sorted += "Sorted:" + test.toString() +"\n";

					if (!is_sorted(test))
					{
						System.out.printf("Error encountered. Array not sorted!\n");
						return;
					}

					if (time_sorted > timeout_threshold_seconds)
					{
						System.out.println("Sorting sorted array taking too long");
						do_sorted = false;
						time_sorted = -1;
					}

				}
				catch (java.lang.OutOfMemoryError error)
				{
					System.out.println(" Sorting the sorted numbers caused an out of memory error");
					do_sorted = false;
					time_sorted = -1;
				}
				catch (java.lang.StackOverflowError error)
				{
					System.out.println(
							" Stack Overflow - Probably sorting the sorted numbers caused an stack overflow error");
					do_sorted = false;
					time_sorted = -1;
				}
			}
			////////////////////////////////////////////////////////////////
			// TEST REVERSE SORTED
			if(do_reverse) {
				try{
					test = generate_reverse_sorted_array(count);
					arrays += "Reverse:" + test.toString() +"\n";
					timer_on();
					sort_routine.sort(test);
					timer_off();
					time_reverse_sorted = get_time();
					arrays_sorted += "Reverse:"+ test.toString() +"\n";

					if (!is_sorted(test))
					{
						System.out.printf("Error encountered. Array not sorted!\n");
						return;
					}

					if (time_reverse_sorted > timeout_threshold_seconds)
					{
						System.out.println("Sorting reverse sorted array taking too long");
						do_reverse = false;
						time_reverse_sorted = -1;
					}

				}
				catch (java.lang.OutOfMemoryError error)
				{
					System.out.println(" Sorting the reverse sorted numbers caused an out of memory error");
					do_reverse = false;
					time_reverse_sorted = -1;
				}
				catch (java.lang.StackOverflowError error)
				{
					System.out.println(
							" Stack Overflow - Probably sorting the reverse sorted numbers caused an stack overflow error");
					do_reverse = false;
					time_reverse_sorted = -1;
				}
			}
			//////////////////////////////////////////////////////////////////////
			// Print out timing information!

//			System.out.printf("%8d\t%s\t%s\t%s\t%s\t", count,
//					time_sorted < 0 ?         "     " : String.format("%5.2f", time_sorted),
//					time_reverse_sorted < 0 ? "     " : String.format("%5.2f", time_reverse_sorted),
//					time_random < 0 ?         "     " : String.format("%5.2f", time_random),
//					time_all_same < 0 ?       "     " : String.format("%5.2f", time_all_same));

			double time_worst = Math.max(time_random, Math.max(time_sorted, Math.max(time_reverse_sorted, time_all_same)));

			//if (sort_routine.get_expected_complexity_class() == Complexity_Class.N_log_N) //IS N log N then divide time by N Log N
			{
				double time_random_divided_by_N_log_N = time_random/(count * Math.log(count));
				double time_worst_divided_by_N_log_N = time_worst/(count * Math.log(count));

			}
			Timing_Data timing_data = new Timing_Data(count,time_sorted, time_reverse_sorted,time_random,time_all_same);
			data.addAll(timing_data);
			array_unsorted = new TextArea(arrays);
			array_sorted = new TextArea(arrays_sorted);
			System.out.flush();
		}

	}

	/**
	 * Insertion Sort.
	 *
	 *         defined by the actual array from [start --> end]
	 */
	public static <Type extends Comparable<? super Type>> void insertion_sort(ArrayList<Type> array, int start,int end ) {
		for (int i = start; i <= end; i++) {
			Type index = array.get(i);
			int j = i;
			while (j > 0 && (array.get(j - 1).compareTo(index)) > 0) {
				array.set(j,array.get(j - 1));
				j--;
			}
			array.set(j,index);
		}
	}

	/**
	 * test the sorting routines with RANDOM data but changing the "constant"
	 * for the sort every time. This is useful to see what the best
	 * GAP for shell sort is or the best Insertion Sort Cutoff for Quick Sortis
	 *
	 *
	 * @param sort_routine
	 *            - which routine to test for various "constant" changes
	 * @param start_value
	 *            - start value of "constant"
	 * @param increment
	 *            - increment value of "constant"
	 * @param end_value
	 *            - final value of "constant"
	 * @param items_to_sort
	 *            - number of items to sort on
	 */
	public void time_constant_change(
										Sorter<Integer> sort_routine, double start_value, double increment,
										double end_value, int items_to_sort )
	{

		final int total = items_to_sort;

		System.out.printf("Sorting Using %s: (Cutoff, Time (Seconds), Constant(*10e6) (of %d elements)\n",
				sort_routine.name_of_sort(), total);

		double time_random = 0;

		ArrayList<Integer> sample = new ArrayList<Integer>(items_to_sort);

		System.out.printf("Insertion Sort Cutoff\tTotal Time\t Constant = time/NLogN\n");

		for (double cutoff = start_value; cutoff <= end_value; cutoff += increment)
		{
			// Slow things down so we can get other work (like debugging)
			// done
			/*
			 * try
			 * {
			 * Thread.sleep(10);
			 * }
			 * catch (InterruptedException exception)
			 * {
			 * // should never happen...
			 * exception.printStackTrace();
			 * }
			 */
			///////////////////////////////////////////////////
			// TEST RANDOM
			//
			// Make X runs over random data and average time

			time_random = 0;
			sort_routine.set_constant(cutoff);
			int runs = 10;

			for (int run = 0; run < runs; run++)
			{

				sample = generate_random_array(total, total);

				timer_on();
				sort_routine.sort(sample);
				timer_off();

				if (!is_sorted(sample))
				{
					System.out.printf("Error encountered Sorting an array of random numbers. Array not sorted!\n");
					time_random = -1;
					return;
				}

				time_random += get_time();
			}

			time_random /= runs;

			System.out.printf("%8d\t%9.4f\t%10.4f\n", (int) cutoff, time_random,
					1000000.0 * time_random / (total * (Math.log(total) / Math.log(Math.E))));

			System.out.flush();
		}
	}

	/**
	 * start the timer
	 */
	private void timer_on()
	{
		start_time = System.nanoTime();
	}

	/**
	 * turn off the timer
	 */
	private void timer_off()
	{
		end_time = System.nanoTime();
	}

	/**
	 * get the time in seconds between on and off.
	 */
	private double get_time()
	{
		return (end_time-start_time);
	}

}
