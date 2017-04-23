package cs2420;

import static cs2420.Utility.printable_symbol;

/**
 * Represents a single node of a Huffman Tree.
 */
class Node implements Comparable<Node>
{
	
	private String symbol;
	private Node left;
	private Node right;
	private Node parent;
	private int  frequency;

	// FIXME:
	//
	//  add private data for:
	//   parent -- references to other nodes
	//   
	//
	
	/**
	 * Constructs a leaf node.
	 * 
	 * @param sym
	 *            - the symbol
	 * @param frequency
	 *            - how often the symbol occurs
	 */
	public Node( String sym, int frequency )
	{
		this.symbol = sym;
		this.frequency = frequency;
		left=right=parent=null;
	}

	/**
	 * Constructs an internal node. Note that a non-leaf/internal node has a weight (sum of the weights of its children)
	 * but no character.
	 * 
	 * @param left
	 *            - left child of the new node
	 * @param right
	 *            - right child of the new node
	 */
	public Node( String sym, Node left, Node right )
	{
		this.symbol = sym;
		this.left = left;
		this.right = right;
		this.parent = null;
		this.frequency = left.frequency + right.frequency;
	}
	
	/**
	 * @return the symbol associated with the node
	 */
	public String get_symbol()
	{
		return this.symbol;
	}
	
	/**
	 * concisely print the Node
	 */
	public String toString()
	{
		if(leaf()){
			return this.symbol;
		}else {
			if(left != null && right !=null) {
				return this.symbol + "left: " + left.symbol + " right: " + right.symbol;
			}else if(left == null  && right != null){
				return this.symbol + "left: null" + "right: " + right.symbol;
			}else if(left != null  && right == null){
				return this.symbol + "left: "+ left.symbol + "right: null";
			}
		}
		return null;
	}

	/**
	 * @return true if a leaf (valid symbol) node
	 */
	boolean leaf()
	{
		if(left == null && right == null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Setter for parent node
	 * @param parent
	 */
	public void set_parent( Node parent )
	{
		this.parent = parent;
	}
	
	/**
	 * @return the parent of this node
	 */
	public Node get_parent()
	{
		return this.parent;
	}

	/**
	 * @return the left child of the parent of this node
	 */
	public Node parents_left()
	{
		return this.parent.left;
	}

	/**
	 * @return the right child of the parent of this node
	 */
	public Node parents_right(){return this.parent.right;}

	/**
	 * @return the weight (frequency of appearance) associated with this node
	 */
	public int get_frequency()
	{
		return this.frequency;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	/**
	 * add one to the frequency field
	 */
	public void increment_frequency()
	{
		frequency++;
	}

	/**
	 * WARNING: only call this method on the "root" of the tree
	 * 
	 * Returns the symbol encoded by a bit string, by traversing the path
	 * from the root of the tree to the leaf node containing the character.
	 * A '0' in the bit string causes the path to follow a left child, and a '1'
	 * in the bit string causes the path to follow a right child.
	 * 
	 * @param code
	 *            - bit string to be decoded, such as "01010001"
	 * @return return null if the bit string does not lead to a symbol, otherwise return the symbol string
	 */
	String get_symbol( String code )
	{
		Node curr = this;
		for (int i = 0; curr != null && i < code.length(); i++) {
			if (code.charAt(i) == '0') {
				curr = curr.left;
			}
			if(code.charAt(i) == '1'){
				curr = curr.right;
			}
		}
		if (curr == null || !curr.leaf()) {
			return null;
		}
		return curr.symbol;
	}



	/**
	 * @return the left most child of this node
	 */
	private Node left_most_child()
	{
		Node temp = this;
		while(temp.left != null){
			temp = temp.left;
		}
		return temp;
	}

	/**
	 * Compare to Huffman nodes, using frequencies.
	 * 
	 * @param rhs
	 *            - right-hand side node
	 * @return a value > 0 if this node is larger than rhs,
	 *         a value < 0 if this node is smaller than rhs, 0 if the
	 *         nodes are equal
	 *         
	 *         larger means node occurs more often (has a higher frequency).
	 *         when tied, compare the symbol of this node's left most child vs the symbol of
	 *         rhs.left_most_child
	 */
	public int compareTo( Node rhs )
	{
		if(this.frequency > rhs.frequency){
			return 1;
		}else if(this.frequency < rhs.frequency){
			return -1;
		}else{
			return this.left_most_child().symbol.compareTo(rhs.left_most_child().symbol);
		}
	}


	// ------------------   DOT description methods and data -------------------------------           
	
	static int null_count = 0;

	/**
	 * write the edges in the graph in the form
	 * used by the DOT language
	 *
	 */
	public String createDot()
	{
		String result = "";

		null_count = 0;

		result += "digraph huffman_tree{\n";

		result += "\thuffman_root -> " + this.symbol + ";\n";
		result += "\thuffman_root [shape=\"none\"];\n";

		// recursively build the dot file
		result += write_dot_helper();

		// create all the null values so they look goodn
		for (int i = 0; i < null_count; i++)
		{
			result += "null" + i + " [shape=point];\n";
		}

		result += "}";

		return result;
	}

	/**
	 * create the DOT syntax for a graph
	 *
	 */
	public String write_dot_helper()
	{
		String result = "";

		String label = printable_symbol(this.symbol);

		if (  leaf() ) 
		{
			result += "\t" + label + " [label=\"" + label + "\\n" + this.frequency + "\"]\n";

			return result; 
		}

		result += "\t" + label + " [label=\"" + label + "\\n" + this.frequency + "\"]\n";

		if (this.left == null || this.right == null)
		{
			System.out.println("ERROR");
			throw new RuntimeException(" nodes must eith have 0 or 2 children");
		}

		String left_label  = printable_symbol(left.symbol);
		String right_label = printable_symbol(right.symbol);

		result += "\t" + label + "-> " + left_label + "[ label=0 ]\n";
		result += "\t" + label + "-> " + right_label + "[ label=1 ]\n";
		result += this.left.write_dot_helper();
		result += this.right.write_dot_helper();

		return result;
	}



}
