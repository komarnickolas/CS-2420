package cs2420;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	/**
	 * The 'root' of our list, around which everything else is sorted.
	 */
	public Node<Type> root;
	private int size;

	/**
	 * Constructs an empty binary search tree with its first node pointing to
	 * nulls.
	 */
	public BinarySearchTree() {
		root = new Node<Type>(null);
		size = 0;
	}

	@Override
	public boolean add(Type item) {
		if (isEmpty()) {
			root.data = item;
			size++;
			return true;
		} else {
			if (root.contains(item)) {
				return false;
			} else {
				root.insert(item);
				if (!root.contains(item)) {
					return false;
				}
				size++;
				return true;
			}
		}
	}

	/**
	 * Iterates over all the elements in items and adds them to our Binary
	 * Search Tree
	 * 
	 * @see cs2420.SortedSet#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean isAltered = false;
		for (Type item : items) {
			if (this.add(item)) {
				isAltered = true;
			}
		}
		return isAltered;
	}

	@Override
	public void clear() {
		root = new Node<Type>(null);
		size = 0;
	}

	@Override
	public boolean contains(Type item) {
		if (isEmpty()) {
			return false;
		}
		return root.contains(item);
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for (Type item : items) {
			if (this.contains(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Type first() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("BST is empty.");
		}
		return root.getLeftMost().data;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Type last() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("BST is empty.");
		}
		return root.getRightMost().data;
	}

	@Override
	public boolean remove(Type item) {
		if(size() == 1){
			size--;
			root = new Node<Type>(null);
			return true;
		}else if(root.left != null && root.left.data.equals(item) && root.left.numChildren() == 0){
			size--;
			root.left = null;
			return true;
		}else if(root.right != null && root.right.data.equals(item) && root.right.numChildren() == 0){
			size--;
			root.right = null;
			return true;
		}else {
			// find item to remove
			if (!contains(item)) {
				return false;
			} else {
				size--;
				return root.delete(item, false);
			}
		}
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		boolean isAltered = false;
		for (Type item : items) {
			if (this.remove(item)) {
				isAltered = true;
			}
		}
		return isAltered;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {

		ArrayList<Type> list = new ArrayList<>();
		arrayListRecursive(list, root);
		return list;
			
	}
	
	private void arrayListRecursive(ArrayList<Type> list, Node<Type> node){
		if(node == null){
			return;
		}
		arrayListRecursive(list, node.left);
		list.add(node.data);
		arrayListRecursive(list, node.right);
	}

	/**
	 *
	 * @param filename - file to write dot file to
	 */
	public void writeDot(String filename) {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(filename));
			output.println("digraph BST {");
			output.println("node [shape=record]");
			if (root != null) {
				writeDotRecursive(root, output);
			}
			output.println("}");
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeDotRecursive(Node<Type> node, PrintWriter output) throws Exception {
		output.println(node.data + "[label=\"<L> |<D> " + node.data + "|<R> \"]");
		if (node.left != null) {
			writeDotRecursive(node.left, output);
			output.println(node.data + ":L -> " + node.left.data + ":D");
		}
		if (node.right != null) {
			writeDotRecursive(node.right, output);
			output.println(node.data + ":R -> " + node.right.data + ":D");
		}
	}

	/**
	 * FIXME: comments
	 *
	 * Pictorially, a node is:
	 *
	 * left data right
	 * 
	 * ---------------
	 * 
	 * <--+ | 5 | +--->
	 * 
	 * ---------------
	 * 
	 * Note, while a 5 is used above any "Type" could be contained in the node
	 */
	/**
	 * @author JenniferNelson
	 *
	 * @param <Type>
	 */
	static class Node<Type extends Comparable<? super Type>> {
		Type data;
		Node<Type> left;
		Node<Type> right;
		Node<Type> parent;

		// FIXME: write a constructor that simplifies building an initial node
		Node(Type the_data) {
			this.data = the_data;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		/**
		 * 
		 * This function must be written recursively.
		 *
		 * Height is defined as the 1 plus the maximum height of the left vs
		 * right sub tree
		 * 
		 * @return the height from this node to its leaves
		 * 
		 * 
		 */
		int height() {
			return this.height(0);
		}

		/**
		 * Helper method to recursively determine the height of this node
		 * 
		 * @param aboveHeight
		 *            The number of nodes 'above' this one
		 * @return the maximum number of nodes below this node, plus the number
		 *         of nodes above.
		 */
		int height(int aboveHeight) {
			aboveHeight++;
			if (left == null && right == null) {
				return aboveHeight;
			}
			if (left == null) {
				return right.height(aboveHeight);
			}
			if (right == null) {
				return left.height(aboveHeight);
			} else {
				return Math.max(left.height(aboveHeight), right.height(aboveHeight));
			}
		}

		/**
		 * recursive determine if the item is in this node or the nodes after
		 * 
		 * @param item
		 *            - needle
		 * @return true if item in tree
		 */
		boolean contains(Type item) {
			if (this.data.equals(item)) {
				return true;
			} else {
				if (item.compareTo(this.data) < 0) {
					if (this.left != null) {
						return left.contains(item);
					} else if (this.right == null) {
						return false;
					} else {
						return right.contains(item);
					}
				}
				if (item.compareTo(this.data) > 0) {
					if (this.right != null) {
						return right.contains(item);
					} else if (this.left == null) {
						return false;
					} else {
						return left.contains(item);
					}
				}
			}
			return false;
		}

		/**
		 *
		 * @param item - item to find in child nodes
		 * @return - if item is in child nodes, returns the node containing it
		 */
		Node<Type> get(Type item) {
			if (this.data.equals(item)) {
				return this;
			} else {
				if (item.compareTo(this.data) < 0) {
					if (this.left != null) {
						return left.get(item);
					} else if (this.right == null) {
						return null;
					}
				}
				if (item.compareTo(this.data) > 0) {
					if (this.right != null) {
						return right.get(item);
					} else if (this.left == null) {
						return null;
					}
				}
			}
			return null;
		}

		/**
		 *
		 * @param item - item to delete from node or nodes children
		 * @param left - if this node is to the left of it's parent node
		 * @return - true if it deletes the item
		 */
		boolean delete(Type item, boolean left){
			if(this.data.equals(item)){
					Node<Type> successor = this.getSuccessor();
					if(successor != null) {
						Node<Type> parent = successor.parent;
						Node<Type> right = successor.right;
						if (parent.left == successor) {
							parent.left = right;
						} else if (parent.right == successor) {
							parent.right = right;
						}
						if (right != null) {
							right.parent = parent;
						}
						this.data = successor.data;
					}else{
						if (parent.left == this) {
							parent.left = null;
						} else if (parent.right == this) {
							parent.right = null;
						}
					}
					return true;
			}else if(this.data.compareTo(item) > 0){
				return this.left.delete(item, true);
			}else if(this.data.compareTo(item) < 0){
				return this.right.delete(item, false);
			}
			return false;
		}

		/**
		 * recursive - add a node
		 * 
		 * @param item
		 *            - data to add
		 * @return
		 */
		void insert(Type item) {
			if (item.compareTo(this.data) > 0) {
				if (right == null) {
					right = new Node<>(item);
					right.parent = this;
				} else {
					right.insert(item);
				}
			}
			if (item.compareTo(this.data) < 0) {
				if (left == null) {
					left = new Node<>(item);
					left.parent = this;
				} else {
					left.insert(item);
				}
			}
			// if item == our data, we don't wanna add anything.
		}

		/**
		 * Number of children this node has
		 * @return - 0 if none, 1 if left or right is not null, 2 if both left and right are not null
		 */
		int numChildren(){
			int numChilderen = 0;
			if(left != null){
				numChilderen++;
			}
			if(right != null){
				numChilderen++;
			}
			return numChilderen;
		}

		/**
		 * @return - Successor node to replace this one when deleted
		 */
		Node<Type> getSuccessor() {
			Node<Type> successor = null;
			if(right != null){
				successor = right.getLeftMost();
			} else if(left != null){
				successor = left.getRightMost();
			}
			return successor;

		}

		/**
		 * @return - farthest right node from this one
		 */
		Node<Type> getRightMost(){
			if(right == null){
				return this;
			}
			return right.getRightMost();
		}

		/**
		 * @return - farthest left node from this one
		 */
		Node<Type> getLeftMost() {
			if(left == null){
				return this;
			}
			return left.getLeftMost();
		}

	}

}
