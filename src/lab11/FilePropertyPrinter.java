package lab11;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * For all standard files on the file system, this will 
 * print out all the properties Java knows about. 
 * @author ryans
 */
public class FilePropertyPrinter implements PropertyPrinter {
	
	private File file;
	private PathType type;
	
	/**
	 * 
	 * @param file - The files whose properties we're curious about
	 * @param pathType - the path type that the user inputed.
	 */
	public FilePropertyPrinter(File file, PathType pathType) {
		this.file = file;
		this.type = pathType;
	}

	@Override
	public void print() {
		BasicFileAttributes bfa = getBasicFileAttributes();
		System.out.println("Printing out properties for " + file.getName());
		if(bfa.isDirectory()) {
			System.out.println("This file is a directory.");
		}
		System.out.println("Path type:\t\t" + this.type);
		System.out.println("Size: \t\t\t" + bfa.size());
		System.out.println("Created: \t\t" + formatDate(bfa.creationTime()));
		System.out.println("Modified: \t\t" + formatDate(bfa.lastModifiedTime()));
		System.out.println("Accessed: \t\t" + formatDate(bfa.lastAccessTime()));
	}
	
	// I rely heavily on the Java's FileAttribute functionality here.
	private BasicFileAttributes getBasicFileAttributes() {
		try {
			URI uri = file.toURI();
			return Files.readAttributes(Paths.get(uri), BasicFileAttributes.class);
		} catch (IOException e) {
			throw new LabElevenException(e);
		}
	}
}
