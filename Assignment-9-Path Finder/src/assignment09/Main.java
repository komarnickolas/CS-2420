package assignment09;

/**
 *
 * @author Jay Heiland, Nickolas Komarnitsky
 *
 */
public class Main {

	public static void main(String[] args) {
		// The below code assumes you have a file "tinyMaze.txt" in your project folder.
        // If PathFinder.solveMaze is implemented, it will create a file
        //   "tinyMazeOutput.txt" in your project folder.
        //  * REMEMBER - You have to refresh your project to see the output
        // file in your package explorer.
        // You are still required to make JUnit tests...just looking at text files ain't going to fly. 
        try {
			PathFinder.solveMaze("Sample Mazes/randomMaze.txt", "Sample Maze Solutions/veryTinyOpenMazeOutput.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
