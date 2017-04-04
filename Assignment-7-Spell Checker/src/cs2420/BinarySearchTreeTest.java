package cs2420;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBinarySearchTreeConstructor() {
		BinarySearchTree<Integer> empty = constructAndTest();
	}

	public BinarySearchTree<Integer> constructAndTest() {
		BinarySearchTree<Integer> empty = new BinarySearchTree<Integer>();
		assertTrue(empty.isEmpty());
		assertTrue(empty.size() == 0);
		// assertTrue(empty.toArrayList().equals(new ArrayList<Integer>()));
		try {
			empty.first();
			fail("Should throw noSuchElementException");
		} catch (NoSuchElementException e) {
			// do nothing, success.
		}
		try {
			empty.last();
			fail("Should throw noSuchElementException");
		} catch (NoSuchElementException e) {
			// do nothing, success.
		}
		return empty;
	}

	@Test
	public void testAdd() {
		BinarySearchTree<Integer> tree = constructAndTest();

		tree.add(50);

		tree.writeDot("Resources/beforeAdd.dot");

		// Add second case:
		tree.add(0);

		// Add larger than case
		tree.add(100);

		// Add smaller than case:
		tree.add(5);

		// Bulk testing: random numbers
		Random rnd = new Random();
		for (int num = 0; num < 1000; num++) {
			int rndNext = rnd.nextInt(101);
			tree.add(rndNext);
		}
		// Bc randomness, could be false. Double check with writeDot
		assertEquals((Integer) 0, tree.first());
		assertEquals((Integer) 100, tree.last());

		tree.writeDot("Resources/afterAdd.dot");
	}

	@Test
	public void testAddAll() {
		BinarySearchTree<Integer> tree = constructAndTest();

		ArrayList<Integer> toAdd = new ArrayList<>();
		assertFalse(tree.addAll(toAdd));
		assertFalse(tree.containsAll(toAdd));
		
		toAdd.add(050);
		assertTrue(tree.addAll(toAdd));
		assertTrue(tree.containsAll(toAdd));
		toAdd.add(000);
		toAdd.add(100);
		toAdd.add(030);
		assertTrue(tree.addAll(toAdd));
		assertTrue(tree.containsAll(toAdd));
		toAdd.add(030);
		toAdd.add(030);
		toAdd.add(030);
		toAdd.add(030);
		assertFalse(tree.addAll(toAdd));
		assertTrue(tree.containsAll(toAdd));
	}

	@Test
	public void testClear() {
		BinarySearchTree<Integer> tree = constructAndTest();

		// Bulk testing: random numbers
		Random rnd = new Random();
		for (int num = 0; num < 1000; num++) {
			int rndNext = rnd.nextInt(101);
			tree.add(rndNext);
		}
		assertFalse(tree.isEmpty());
		tree.clear();
		assertTrue(tree.isEmpty());
	}

	@Test
	public void testContains() {
		BinarySearchTree<Integer> tree = constructAndTest();

		// Bulk testing: random numbers
		Random rnd = new Random();
		for (int num = 0; num < 1000; num++) {
			int rndNext = rnd.nextInt(101);
			tree.add(rndNext);
			assertTrue(tree.contains(rndNext));
		}
		for (int removing = 0; removing < 101; removing++) {

			tree.remove(removing);
			tree.writeDot("Resources/contains" + removing +".dot");
			assertFalse(tree.contains(removing));
		}

		// tree.writeDot("Resources/afterContains.dot");
	}

	@Test
	public void testIsEmpty() {
		testClear();

	}

	@Test
	public void testFirstAndLast() {
		BinarySearchTree<Integer> tree = constructAndTest();

		tree.add(50);
		assertEquals((Integer) 50, tree.last());
		assertEquals((Integer) 50, tree.first());

		tree.add(0);
		assertEquals((Integer) 50, tree.last());
		assertEquals((Integer) 0, tree.first());

		tree.add(100);

		assertEquals((Integer) 100, tree.last());
		assertEquals((Integer) 0, tree.first());

	}

	@Test
	public void testRemove() {
		BinarySearchTree<Integer> tree = constructAndTest();

		tree.add(50);
		tree.add(0);
		tree.add(100);

		// Bulk testing: random numbers
		Random rnd = new Random();
		for (int num = 0; num < 1000; num++) {
			int rndNext = rnd.nextInt(101);
			tree.add(rndNext);
		}

		tree.writeDot("Resources/beforeRemove.dot");

		for (int removing = 0; removing < 101; removing++) {
			tree.remove(removing);
				tree.writeDot("Resources/" + removing + "Remove.dot");
		}

		tree.writeDot("Resources/afterRemove.dot");

		assertTrue(tree.isEmpty());

	}

	@Test
	public void testRemoveAllAndContainsAll() {
		BinarySearchTree<Integer> tree = constructAndTest();

		tree.add(50);
		tree.add(0);
		tree.add(100);

		// Bulk testing: random numbers
		Random rnd = new Random();
		for (int num = 0; num < 1000; num++) {
			int rndNext = rnd.nextInt(101);
			tree.add(rndNext);
		}

		ArrayList<Integer> toRemove = new ArrayList<>();

		assertFalse(tree.removeAll(toRemove));
		assertFalse(tree.containsAll(toRemove));
		toRemove.add(50);
		assertTrue(tree.removeAll(toRemove));
		assertFalse(tree.containsAll(toRemove));
		toRemove.add(100);
		toRemove.add(0);
		toRemove.add(39);
		toRemove.add(19);
		toRemove.add(52);
		toRemove.add(80);

		assertTrue(tree.removeAll(toRemove));
		assertFalse(tree.containsAll(toRemove));
	}

	@Test
	public void testSize() {
		BinarySearchTree<Integer> tree = constructAndTest();

		// Bulk testing:
		for (int num = 0; num < 100; num++) {
			tree.add(num);
			assertEquals(num + 1, tree.size());
		}
		// DBC After remove fixed, does this work?
		for (int removing = 0; removing < 100; removing++) {
			if (removing % 10 == 0) {
				tree.writeDot("Resources/after" + removing + "Removals.dot");
			}
			tree.remove(removing);
			assertEquals(99 - removing, tree.size());
		}
		assertEquals(0, tree.size());

	}

	@Test
	public void testToArrayList() {
		BinarySearchTree<Integer> tree = constructAndTest();
		ArrayList<Integer> array = new ArrayList<>();
		// Bulk testing: DBC Order
		for (int num = 0; num < 100; num++) {
			tree.add(num);
			array.add(num);
			assertEquals(array, tree.toArrayList());
		}
		assertEquals(array, tree.toArrayList());

	}

}
