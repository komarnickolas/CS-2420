package lab11;

import java.io.Console;

/**
 * This is the main class for the FileFinder (notice the main method?).
 * It has a infinite loop that reads, evaluates and prints out responses. This is 
 * commonly known as a REPL. 
 * @author ryans
 *
 */
public class FileFinder {
	
	private final Console console = System.console();
	private Evaluator evaluator;
	
	public FileFinder() {
		evaluator = new Evaluator();
		run();
	}

	private void run() {
		System.out.println("Welcome to the File Finder!");
		
		//Classic read/eval loop.
		while(true) {
			System.out.print("?> ");
			String input = read();
			eval(input);
		}
	}
	
	// The evaluator throws execptions, so it's helpful to have a method that will catch anything
	// that is thrown to keep the loop infinite.
	private void eval(String command) {
		if(command == null || command.trim().isEmpty()) {
			return; // if there is no useful input, return to prompt
		}
		try{
			evaluator.evaluate(command);
		} catch (LabElevenException labTenError) {
			System.out.println(labTenError.getMessage());
		} catch (Throwable t) {
			System.out.println("This is...unexcepted.");
			t.printStackTrace();
		}
	}

	private String read() {
		return console.readLine();
	}

	public static void main (String[] args) {
		new FileFinder();
	}
}
