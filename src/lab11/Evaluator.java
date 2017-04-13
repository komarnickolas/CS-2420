package lab11;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The work horse of the REPL. This class parses the users input
 * and calls the proper methods. 
 * @author ryans
 */
public class Evaluator {
	/**
	 * Evaluate uses the switch construct, with an Enum argument. Enums work well with switches
	 * and vise-versa, as there are compile time warnings if a condition is not handled. 
	 */
	public void evaluate(String command) {
		String[] tokens = tokenfy(command);	
		switch (getActionFromCommand(tokens[0])) {
		case READ:
			read(tokens); break;
		case WRITE:
			write(tokens); break;
		case DECODE:
			decode(tokens); break;
		case LIST:
			list(tokens);
			break;
		case EXIT: 
			System.out.println("Goodbye!");
			System.exit(0);
		case UNKNOWN:
			System.out.println("Unknown command: '" + tokens[0] + "'");
			break;
		}
	}
	/*
	 * The methods below correlate with the possible user actions. 
	 * Each does a quick validity check to avoid any index out of bounds errors.
	 */
	private void list(String[] tokens) {
		if(tokens.length != 2) {
			System.out.println("Please supply a path to a directory.");
			return;
		}
		File directory = new File(tokens[1]);
		if(!directory.exists()) {
			throw new LabElevenException("File not found: " + tokens[1]);
		}
		if(!directory.isDirectory()) {
			read(tokens);
			return;
		}
		
		String output = new Lister().listFiles(directory);
		System.out.println(output);
		
	}

	private void read(String[] tokens) {
		if(tokens.length != 2) {
			System.out.println("Please supply a path to a file.");
			return;
		}
		
		PropertyPrinter printer = PropertyPrinterBuilder.makePrinterFor(tokens[1]);
		printer.print();
	}
	
	private void write(String[] tokens) {
		if(tokens.length != 2) {
			System.out.println("Please supply a path to a file.");
			return;
		}
		
		File f = new File(tokens[1]);
		try {
			if(f.createNewFile()) {
				System.out.println("Successfully wrote file to: " + f.getCanonicalPath());
			} else {
				System.out.println("That file already exists!");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void decode(String[] tokens){
		if(tokens.length != 3) {
			System.out.println("Invalid format, please supply password");
			return;
		}
		Decoder decoder = new Decoder(tokens[1], tokens[2]);
		decoder.decode();
	}
	
	/* end action methods */
	private Action getActionFromCommand(String modecommand) {
		Action action = Action.UNKNOWN; //default action.
		for(Action value : Action.values()){
			if(value.toString().indexOf(modecommand.toUpperCase()) == 0) {
				/* see comments on 'assert' keyword below*/
				assert action == null : "Command should not match multiple actions. " + action + "matched " + action + " and " + value;
				action = value;
			}
		}
		return action;
	}
	
	private String[] tokenfy(String command) {
		// Code from http://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(command);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		        matchList.add(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		}
		return matchList.toArray(new String[matchList.size()]);
	}
	
	/*
	 * Didn't know there was an assert keyword, did ya!
	 * Using Java's assert keyword is generally frowned upon in high society, but Assertion based programming is a popular philosophy.
	 * I am showing a bit of good with the bad. The assert keyword throws and Assertion Error if the condition before the colon is FALSE, and displays the message 
	 * after the colon.                                                                                                                               
	 */
	
	/**
	 * The operations supported by this REPL
	 * READ - read file properties
	 * WRITE - create an empty file
	 * DECODE - decrypt file for passwords!
	 * UNKNOWN - default action
	 * EXIT - Get outta here.
	 * @author ryans
	 */
	public enum Action {
		READ, WRITE, DECODE, UNKNOWN, EXIT, LIST
	}
}
