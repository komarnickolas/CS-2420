package assignment09;
/**
 *
 * @author Jay Heiland, Nickolas Komarnitsky
 *
 */
public class Node {
	//visited flag
	public boolean visited = false;
	
	//0 -- wall, 1 -- open space, start node, end node
	public Node[] edges = new Node[4];
	
	//numeric identifier
	public int id;

	//Number of times looked at
	public int lookedAt;
	
	//corresponding output file symbol ("X", " ", "S", "G", ".")
	public String fileSymbol;
	
	//array of neighbor nodes
	public Node(int id_){
		id = id_;
		lookedAt = 0;
	}
	
	//assigned in path finding algorithm or left null
	public Node prevNode = null;
	
	//note: need to choose either ArrayDeque or LinkedList to store all Nodes in maze
	//may need to use arraydeque because it is faster than linkedlist when used as a queue
}
