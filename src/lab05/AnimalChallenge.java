package lab05;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

import lab05.zoo.Zoo;
import lab05.zoo.Zoo.Animal;
import lab05.zoo.Zoo.Pet;
import lab05.zoo.ZooKeeper;


/**
 * I agree not to talk about this lab until Tuesday, Feb 14 2017
 * @author Nickolas Komarnitsky
 * 
 *  Please ask if any of the directions are unclear, we want you all to succeed.
 *  This file as a main method you can run at any time to check your progress 
 *  on Task 1. Tasks 2 - 4 will require your own testing.
 *  
 *  PLEASE test Task 1 before moving on. So many points possible!
 */
public class AnimalChallenge<T> {

	/**
	 * @name: insertInto
	 * This method visible to everyone, and is static.
	 * 
	 * Inserts an element into a collection in the proper, sorted position. The
	 * collection is sorted in ascending order, meaning low to high. 
	 * The collection parameter is already sorted with null values padding the end.
	 * There is space available for the inserted element. 
	 * 
	 * Once you get the method header written you should run your program to ensure
	 * it is defined properly. (We have written this functionality for you, just 
	 * trust me and run it).
	 * 
	 * You can think of the collection parameter looking like this:
	 * [element0, element1, element2, ... , elementN, null, null, ... ,null ]
	 * 
	 * @param collection
	 *            - the generic basic array to insert into. GUARENTEED to already be
	 *            in sorted order, and have enough space for the new element (nulls pad the end).
	 * @param element
	 *            - The generic item to insert into collection.
	 * @param comparator
	 *            - Used for sorting. Don't forget to parameterize it! 
	 * 
	 * @return -- NOTHING! The return type is void, this should MODIFY the
	 *         passed in collection.
	 */
	public void insertInto(T[] collection, T element, Comparator<T> comparator){
		int spot = 0;
		for(int i = 0; i<collection.length; i++){
			if(collection[i] == null){
				collection[i] = element;
			}
			else if(comparator != null){
				if(comparator.compare(element,collection[i]) > 0){
					spot = i;
					break;
				}else if(((Comparable) element).compareTo(collection[i]) > 0){
					spot = i;
					break;
				}
			}
		}
		ArrayList<T> result = new ArrayList<T>();
		for(int i = 0; i < spot; i++) {
			result.add(collection[i]);
		}
		result.add(element);
		for(int i = spot + 1; i < collection.length; i++) {
			result.add(collection[i]);
		}
		result.toArray(collection);
	}
	// **** TASK 1,2 ****
	//TODO: Implement the method described by the JavaDoc above
	
	/**
	 * Class name : AnimalComparator
	 * 
	 * This is a public static class that is used to compare two Animal for sorting.
	 * 
	 * Animals are sorted by their taxonomic classification in lexicographical order.
	 * This means animals are first sorted by their genus, then their species, in 
	 * ascending order.
	 */
	public static class AnimalComparator implements Comparator<Animal>{
		@Override
		public int compare(Animal o1, Animal o2) {
			if(o1.genus.compareTo(o2.genus) > 0){
				if(o1.species.compareTo(o2.species) > 0){
					return 1;
				}else{
					return 0;
				}
			}else if(o1.genus.compareTo(o2.genus) < 0){
				if(o1.species.compareTo(o2.species) < 0){
					return -1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		}
	}
	// **** TASK 1,3
	//TODO: Implement the class described by the above JavaDoc

	
	// **** TASK 4
	// Create new comparators for each method, and return them!
	/** Pet extends Animal. The Pet comparator sorts by cuteness first and foremost, and
	 * then falls back to the default Animal sort to break ties.
	 */
	public static Comparator<Pet> getNewPetComparator() {
		return new Comparator<Pet>() {
			@Override
			public int compare(Pet o1, Pet o2) {
				if(o1.cuteness > o2.cuteness){
					return 1;
				}else if(o1.cuteness < o2.cuteness){
					return -1;
				}else{
					AnimalComparator animalComparator = new AnimalComparator();
					return animalComparator.compare(o1,o2);
				}
			}
		};
	}
	
	/**
	 *	Sort animals by how hungry they are (most hungry first!), and if they have the same hunger level,
	 *  pick who ever has more teeth!
	 */
	public static Comparator<Animal> getNewHungerTeethComparator() {
		return new Comparator<Animal>() {
			@Override
			public int compare(Animal o1, Animal o2) {
				if(o1.hungerLevel > o2.hungerLevel) {
					return 1;
				}else if(o1.hungerLevel < o2.hungerLevel){
					return -1;
				}else{
					if(o1.numOfTeeth > o2.numOfTeeth){
						return 1;
					}else if(o1.numOfTeeth < o2.numOfTeeth){
						return -1;
					}else{
						return 0;
					}
				}
			}
		};
	}
	
	/**
	 * Sort animals by putting predators first. Order animals by their
	 * teeth to height ratio (teeth / height) in descending order.
	 * @return
	 */
	public static Comparator<Animal> getNewPredators_TeethByHeight() {
		return new Comparator<Animal>() {
			@Override
			public int compare(Animal o1, Animal o2) {
				if(o1.isPredator && !o2.isPredator){
					return 1;
				}else if(!o1.isPredator && o2.isPredator){
					return -1;
				}else{
					if(o1.numOfTeeth/o1.height > o2.numOfTeeth/o2.height){
						return 1;
					}else if(o1.numOfTeeth/o1.height < o2.numOfTeeth/o2.height){
						return -1;
					}else{
						return 0;
					}
				}
			}
		};
	}
	
	
	/**
	 * Run this method to see if you have incorrect method and class signatures. 
	 * It is possible to get false positives from this method. It might 'pass', but your
	 * signature will be still be incorrect. 
	 * 
	 * This does not check if your methods are implemented correctly, it only evaluates if 
	 * they are declared correctly.
	 */
	public static void main(String... args) {
		ZooKeeper.gutCheck(); // a helpful method to see if you're on the right track.

		//Don't forget to test insertInto!
		Integer[] testArray = new Integer[]{1, 2, 3, 4, null};
		testArray.toString();
		// You can use testArray when your AnimalComparator.insertInto() method!
//		insertInto(testArray,0,null);
		
		ZooKeeper.getAnimals();
		// and
		ZooKeeper.getPets(); 
		 // will return a list of test data for you to test your getNewComparator methods (task 4).
	}
}
