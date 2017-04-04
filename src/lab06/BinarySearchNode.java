package lab06;

/**
 * A Binary Search Tree is a recursive data structure, so you really only need to implement it's base
 * structure, the Binary Search Node! That's what we'll be doing in todays lab.
 * 
 * @param <T> - IMPORTANT: The BinarSearchNode is generic, but when you write Assignment08 DO NOT have your 
 * inner BinarySearchNode be generic. The BST class should be the only class with type arguments.
 * This class needs to be generic for the purposes of the lab, but if you use this class in Assignment 8, make 
 * sure to remove the type parameters.   
 */

public class BinarySearchNode<T extends Comparable<? super T>> {
	protected T data;
	protected BinarySearchNode<T> left;
	protected BinarySearchNode<T> right;

	/**
	 * Construct a new node with known children
	 */
	public BinarySearchNode(T data, BinarySearchNode<T> left,
			BinarySearchNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * Construct a new node with no children
	 */
	public BinarySearchNode(T data) {
		this(data, null, null);
	}

	/**
	 * Number of children Use this to help figure out which BST deletion case to
	 * perform
	 * 
	 * @return The number of children of this node
	 */
	public int numChildren() {
		int numChildren = 0;
		if (left != null) {
			numChildren++;
		}
		if (right != null) {
			numChildren++;
		}
		
		return numChildren;
	}

	/**
	 * @return The leftmost node in the binary tree rooted at this node.
	 */
	public BinarySearchNode<T> getLeftmostNode() {
		// Please implement RECURSIVELY for lab!
		if (left == null) {
			return this;
		}
		return this.left.getLeftmostNode();
	}
	
	/**
	 * @return The rightmost node in the binary tree rooted at this node.
	 */
	public BinarySearchNode<T> getRightmostNode() {
		// Please implement ITERATIVELY for lab!
		BinarySearchNode<T> temp = this;
		while(temp.right != null){
			temp = temp.right;
		}
		return temp;
	}
	
	/**
	 * @return The height of the binary tree rooted at this node. The height of
	 *         a tree is the length of the longest path to a leaf node. Consider
	 *         a tree with a single node to have a height of zero.
	 * 
	 *         The height of a tree with more than one node is the greater of
	 *         its two subtrees' heights, plus 1
	 */
	public int height() {
		// Please implement recursively for lab!
		if(numChildren() == 0){
			return 1;
		} else if(right == null){
			return 1 + left.height();
		}else if(left == null){
			return 1 + right.height();
		}
		return Math.max(right.height(),left.height()) + 1;
	}

	/**
	 * 
	 * This method applies to binary search trees only (not general binary
	 * trees).
	 * 
	 * @return The successor of this node.
	 * 
	 *         The successor is a node which can replace this node in a case-3
	 *         BST deletion. It is either the smallest node in the right
	 *         subtree, or the largest node in the left subtree.
	 */
	public BinarySearchNode<T> getSuccessor() {
		if (right != null) {
			return right.getLeftmostNode();
		}
		if (left != null) {
			return left.getRightmostNode();
		} else {
			return this;
		}
	}
}
