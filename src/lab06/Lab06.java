package lab06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Run this class to test your implementation of the BinarySearchNode.
 * The first block of code is a demonstration of using the TreeBuilder to build a Binary Search Tree.
 * 
 * Use the writeDot method to generate a "dot file" that can be drawn with Graphviz.  
 * @author ryans
 */
public class Lab06 {
	
	public static void main(String[] args) {
		/**
		 * Enter your uid here.
		 */
		String uid = "u0717854"; //please format the usual way: u0000000
		
		// Quick demo building a BST with your UID. Check out the uid.dot! 
		TreeBuilder<Character> charTreeBuilder = new TreeBuilder<>();
		BinarySearchNode<Character> root = charTreeBuilder.buildTree(getCharList(uid));
		charTreeBuilder.writeDot("uid.dot", root);
		
		//You can use the TreeBuilder with any type!
		TreeBuilder<Integer> numberTreeBuilder = new TreeBuilder<>();
		BinarySearchNode<Integer> numRoot = numberTreeBuilder.buildTree(Arrays.asList(3,4,6,71,12,3,76,8,9));
		numberTreeBuilder.writeDot("numroot.dot", numRoot);
		
		
		// When you have successfully implemented BinarySearchNode, this tester will 
		// validate your code and give you a password for the server! (cs2420-lab.eng.utah.edu)
		TreeTester tester = new TreeTester(uid);
		try{
			tester.testNode();
		} catch (TreeTester.TreeError treeError) {
			System.err.println(treeError.getMessage());
//			BinarySearchNode<Character> root = treeError.getRootNode(); // -- Use this line to debug your BSN. 
		}
		
		// When you're done with the BinarySearchNode, see if you can rearrange the letters in the alphabet to make a perfectly
		// balanced BST. Submit the string you come up with into the server to get your final point! Feel free to write any code to help you!
		TreeBuilder<Character> alphabetTreeBuilder = new TreeBuilder<>();
		root = alphabetTreeBuilder.buildTree(getCharList("abcdefghijklmnopqrstuvwyz"));
		
		charTreeBuilder.writeDot("alphabet.dot", root); // does it look balanced? 
	}
	
	/**
	 * @param string - string you want to turn into a Character list. 
	 * @return
	 */
	private static List<Character> getCharList(String string) {
		List<Character> charList = new ArrayList<>();
		for(char c : string.toCharArray()) {
			charList.add(c);
		}
		return charList;
	}
}
