package assignment09;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
/**
 *
 * @author Jay Heiland, Nickolas Komarnitsky
 *
 */
public class PathFinderTests {

    private RandomMaze randomMaze;
	@Before
	public void setUp() throws Exception {
	    randomMaze = new RandomMaze(20,20);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExceptions() throws Exception {
		try {
			PathFinder.solveMaze("mazes/NotEnclosed.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
		try {
			PathFinder.solveMaze("mazes/WrongHeight.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
		try {
			PathFinder.solveMaze("mazes/WrongHeightHeader.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
		try {
			PathFinder.solveMaze("mazes/WrongWidth.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
		try {
			PathFinder.solveMaze("mazes/WrongWidthHeader.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
		try {
			PathFinder.solveMaze("mazes/NoStart.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
		try {
			PathFinder.solveMaze("mazes/NoEnd.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
		try {
			PathFinder.solveMaze("mazes/NoStartAndEnd.txt");
			fail("Expected an Illegal File Format Exception");
		} catch (Exception exception) {
			assertThat(exception.getMessage(), is("Illegal File Format"));
		}
	}

	@Test
	public void test() throws Exception {
		randomMaze.randomize();
		randomMaze.writeFile("mazes/random.txt");
		assertTrue(PathFinder.solveMaze("mazes/random.txt"));
		PathFinder.writeLookedAt = true;
		PathFinder.solveMaze("mazes/random.txt", "mazes/randomSol.txt");
		PathFinder.writeLookedAt = false;
		assertFalse(PathFinder.solveMaze("mazes/unsolvable.txt"));
		assertTrue(PathFinder.solveMaze("mazes/randomMaze.txt"));
		assertTrue(PathFinder.solveMaze("mazes/turn.txt"));
	}

}
