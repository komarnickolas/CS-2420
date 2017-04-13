package lab11;

import java.io.File;

/**
 * This is a very simple duplication of the 'ls' or 'dir' command. 
 * Lister builds a two-column list of files name in a given directory
 * @author ryans
 *
 */
public class Lister {
	public String listFiles(File directory) {
		StringBuilder sb = new StringBuilder();
		File[] files = directory.listFiles();
		for(int fileIdx = 0; fileIdx < files.length - 1; fileIdx+=2) {
			sb.append(String.format("%-20s %-20s\n", files[fileIdx].getName(), files[fileIdx+1].getName()));
		}
		if((files.length & 1) == 1) {
			sb.append(files[files.length-1] + "\n");
		}
		return sb.toString();
	}
}
