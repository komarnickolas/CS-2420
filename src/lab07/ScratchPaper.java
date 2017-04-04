package lab07;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScratchPaper {

	@Test
	public void simpleLinkedListTest() {
		BiNode zero = new BiNode(0);
		BiNode one   = new BiNode(1);
		BiNode two   = new BiNode(2);
		BiNode three = new BiNode(3);
		BiNode four = new BiNode(4);
		BiNode five = new BiNode(5);
		BiNode six = new BiNode(6);
		BiNode seven = new BiNode(7);
		BiNode eight = new BiNode(8);
		BiNode nine = new BiNode(9);
		BiNode ten = new BiNode(10);
		BiNode eleven = new BiNode(11);
		BiNode twelve = new BiNode(12);
		BiNode thirteen = new BiNode(13);
		BiNode fourteen = new BiNode(14);
		zero.right = one;
		one.right = two;
		two.right = three;
		three.right = four;
		four.right = five;
		five.right = six;
		six.right = seven;
		seven.right = eight;
		eight.right = nine;
		nine.right = ten;
		ten.right = eleven;
		eleven.right = twelve;
		twelve.right = thirteen;
		thirteen.right = fourteen;
		fourteen.right = zero;
		
		assertTrue(BiNode.hasCycle(zero));

		zero.right = one;
		one.right = two;
		two.right = three;
		three.right = four;
		four.right = five;
		five.right = six;
		six.right = seven;
		seven.right = eight;
		eight.right = nine;
		nine.right = ten;
		ten.right = eleven;
		eleven.right = twelve;
		twelve.right = thirteen;
		thirteen.right = fourteen;
		fourteen.right = null;

		assertFalse(BiNode.hasCycle(zero));
	}
	
	@Test
	public void simpleBinaryTreeTest() {
		BiNode zero = new BiNode(0);
		BiNode one   = new BiNode(1);
		BiNode two   = new BiNode(2);
		BiNode three = new BiNode(3);
		BiNode four = new BiNode(4);
		BiNode five = new BiNode(5);
		BiNode six = new BiNode(6);
		BiNode seven = new BiNode(7);
		BiNode eight = new BiNode(8);
		BiNode nine = new BiNode(9);
		BiNode ten = new BiNode(10);
		BiNode eleven = new BiNode(11);
		BiNode twelve = new BiNode(12);
		BiNode thirteen = new BiNode(13);
		BiNode fourteen = new BiNode(14);

		seven.right = eleven;
		seven.left = three;

		three.right = five;
		three.left = one;

		eleven.right = thirteen;
		eleven.left = nine;

		five.right = six;
		five.left = four;

		one.right = two;
		one.left = zero;

		nine.right = ten;
		nine.left = eight;

		thirteen.right = fourteen;
		thirteen.left = twelve;

		assertEquals("02146538109121413117", BiNode.postOrderTraversal(seven));
		assertEquals("", BiNode.postOrderTraversal(null));
	}

}
