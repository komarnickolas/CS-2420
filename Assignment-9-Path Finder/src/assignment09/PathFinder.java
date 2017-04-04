package assignment09;

/**
 *
 * @author Jay Heiland, Nickolas Komarnitsky
 *
 */
public class PathFinder {

	public static boolean writeLookedAt = false;

	/**
	 * This method uses the given header and calls methods in static Graph to solve an input text maze and print the solution maze
	 * (where the shortest path is marked with periods) to an output text file.
	 * @param inputFileName - the String file path to the input maze
	 * @param outputFileName - the String file path to the output maze
	 * @throws Exception
	 */
	public static void solveMaze(String inputFileName, String outputFileName) throws Exception {
		Node[][] nodes = Graph.readGraph(inputFileName);
		nodes = Graph.checkForEdges(nodes);
		Graph.findPath(nodes);
		Graph.markPathBack();
		Graph.writeGraph(outputFileName, nodes, writeLookedAt);
	}
	/**
	 * This method uses the given header and calls methods in static Graph to solve an input text maze
	 * @param inputFileName - the String file path to the input maze
	 * @throws Exception
	 * @return true if maze has been solved, false if no path was found
	 */
    public static boolean solveMaze(String inputFileName) throws Exception {
		Node[][] nodes = Graph.readGraph(inputFileName);
    	nodes = Graph.checkForEdges(nodes);
    	Graph.findPath(nodes);
    	return Graph.markPathBack();
	}
}
