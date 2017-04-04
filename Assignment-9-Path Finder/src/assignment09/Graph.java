package assignment09;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 *
 * @author Jay Heiland, Nickolas Komarnitsky
 *
 */
public class Graph {
	private static int height; //corresponds with "row"
	private static int width; //corresponds with "col"
	private static Node[][] inputNodes;
	
	//location of start node
	private static Node startNode = null;
	//location of end node
	private static Node endNode = null;
	/**
	 * Reads the input maze file into a matrix of Node objects.
	 * @param path_name - the file path to the input maze
	 * @throws IOException
	 */
	public static Node[][] readGraph(String path_name) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(path_name));
		String[] dimensions = input.readLine().split(" ");
		//read and record the dimensions of the maze
	    height = Integer.parseInt(dimensions[0]);
	    width = Integer.parseInt(dimensions[1]);
	    //read and record the each row of nodes in the maze
	    //Types: wall = 0, empty space = 1, start point = 2, end point = 3
	    inputNodes = new Node[height][width];
	    String line;
	    int currentRow = 0;
	    
	    //create an id integer which is then incremented to give each non-wall node a unique id #
		int identity = 0;
	    char[] first = input.readLine().toCharArray();
	    if(first.length != width){
			throw new Exception("Illegal File Format");
		}
	    for(int i = 0; i<first.length; i++){
	    	if(first[i] != 'X'){
	    		throw new Exception("Illegal File Format");
			}else if(first[i] == 'X'){
				inputNodes[currentRow][i] = new Node(-1);
				inputNodes[currentRow][i].fileSymbol = "X";
			}
		}
		currentRow++;
	    while((line = input.readLine()) != null){
	    	if(currentRow >= height || line.length() != width || line.charAt(0) != 'X' || line.charAt(line.length() -1) != 'X'){
	    		throw new Exception("Illegal File Format");
			}

	    	for(int currentCol = 0; currentCol < line.length(); currentCol++){
	    		//wall
	    		if(line.charAt(currentCol) == 'X'){
	    			//add wall node to matrix
	    			inputNodes[currentRow][currentCol] = new Node(-1);
	    			inputNodes[currentRow][currentCol].fileSymbol = "X";
	    		}
	    		//empty space, start point, end point
	    		else{
	    			//add new node to matrix
	    			Node nonWall = new Node(identity);
	    			inputNodes[currentRow][currentCol] = nonWall;
	    			//store the id of the start and end nodes
	    			if(line.charAt(currentCol) == 'S'){
		    			startNode = nonWall;
		    			startNode.fileSymbol = "S";
		    			inputNodes[currentRow][currentCol].fileSymbol = "S";
		    		}
	    			else if(line.charAt(currentCol) == 'G'){
		    			endNode = nonWall;
		    			endNode.fileSymbol = "G";
		    			inputNodes[currentRow][currentCol].fileSymbol = "G";
		    		}
	    			else if(line.charAt(currentCol) == ' '){
	    				inputNodes[currentRow][currentCol].fileSymbol = " ";
		    		}
	    			//increment id
		    		identity++;
	    		}
	    	}
	    	currentRow++;
	    }
		if(currentRow < height || startNode == null || endNode == null){
			throw new Exception("Illegal File Format");
		}
	    return inputNodes;
	}
	/**
	 * Takes the matrix of nodes generated in readGraph and checks for edges (non-wall nodes) around each node to the north, south,
	 * east, and west.
	 * @param nodes - a matrix of Node objects
	 * @return
	 */
	public static Node[][] checkForEdges(Node[][] nodes){
		//for each node in the maze
		for(int row = 0; row < nodes.length; row++){
			for(int col = 0; col < nodes[0].length; col++){
				//if node is not an edge/wall
				if(nodes[row][col].id >= 0){
					//find if there are edges to the north, south, east, or west
					//north
					if(nodes[row-1][col].id >= 0){
						nodes[row][col].edges[0] = nodes[row-1][col];
					}
					//south
					if(nodes[row+1][col].id >= 0){
						nodes[row][col].edges[1] = nodes[row+1][col];
					}
					//east
					if(nodes[row][col+1].id >= 0){
						nodes[row][col].edges[2] = nodes[row][col+1];
					}
					//west
					if(nodes[row][col-1].id >= 0){
						nodes[row][col].edges[3] = nodes[row][col-1];
					}
				}
				//also make startNode and endNode into references to the in-matrix start and end nodes
				if(nodes[row][col].id == startNode.id){
					startNode = nodes[row][col];
				}
				if(nodes[row][col].id == endNode.id){
					endNode = nodes[row][col];
				}
			}
		}
		
		return nodes;
	}
	/**
	 * Takes the final, solved version of the maze and writes its symbols to the output text file.
	 * @param path_name - the file path name of the output maze
	 * @param nodes - the matrix of Nodes representing the final, solved maze
	 * @throws IOException
	 */
	public static void writeGraph(String path_name, Node[][] nodes, boolean writeLookedAt) throws IOException{
	    try(PrintWriter output = new PrintWriter(new FileWriter(path_name)))
	    {	
	    	//write the dimensions of the solution maze
	         output.println(height + " " + width);
	         // write the symbol of each node (doesn't currently print periods representing the path)
	         for(int row = 0; row < height; row++){
	        	 for(int col = 0; col < width; col++){
	        	 	if(writeLookedAt && nodes[row][col].lookedAt > 0 && nodes[row][col].fileSymbol != "X" && nodes[row][col].fileSymbol != "S" && nodes[row][col].fileSymbol != "G"){
						output.print(nodes[row][col].lookedAt);
					}else {
						output.print(nodes[row][col].fileSymbol);
					}
	        	 }
	        	 //return to new line in output file after each line of node information
	        	 output.println();
	         }
	    }
	}

	/**
	 * Uses Dijkstra's algorithm to find the shortest path between the start and end nodes, but without concern for edge weights
	 * because each edge has the same weight.
	 * @param nodes - the matrix of Nodes
	 */

	public static void findPath(Node[][] nodes){
			//initialize all nodes and priority queue (queue stores nodes in path?)
			Queue<Node> PQ = new ArrayDeque<Node>();
			//add start node to queue
			PQ.add(startNode);
			//initialize a current node
			Node curr;
			
			while(!PQ.isEmpty()) {
				//dequeue a new current node
				curr = PQ.poll();
				//base case - current node is the end node
				if(curr.equals(endNode)){
					return; // found goal!
				} 
				//mark current node as visited
				curr.visited = true;
				//foreach unvisited neighbor n of curr that isn't a wall
				for(Node neighbor : curr.edges){
					if(neighbor != null) {
						neighbor.lookedAt++;
						if (neighbor.id >= 0 && !neighbor.equals(startNode) && !PQ.contains(neighbor)) {
							if (!neighbor.visited) {
								neighbor.prevNode = curr;
								PQ.add(neighbor); //update n’s priority
							}
						}
					}
				}
			}
	}
	/**
	 * Small separation of code from the findPath method. Takes the static matrix of Nodes and, starting at the end node, follows
	 * the trail of "previous Node" references back to the start node. Marks the path with periods.
	 * @return true if path exists, false if not
	 */
	public static boolean markPathBack(){
		Node curr = endNode.prevNode;
		if(curr != null){
			while(curr != startNode){
				curr.fileSymbol = ".";
				curr = curr.prevNode;
			}
			return true;
		}
		return false;
	}
	
}
