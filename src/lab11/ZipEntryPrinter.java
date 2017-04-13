package lab11;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;

/**
 * If something is in a jar or in a zip file, it
 * might not give us all the information we want. Extracting the 
 * ZipEntryPrinter into its own class gives us some nice modularity.
 * 
 * The entry itself has the information we're curious about, and we don't have to 
 * get Java's FileAttributes class involved.
 * @author ryans
 */
public class ZipEntryPrinter implements PropertyPrinter {
	
	ZipEntry entry;
	
	public ZipEntryPrinter(URL url) {
		try {
			JarURLConnection conn = (JarURLConnection)url.openConnection();
			entry = conn.getJarEntry();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void print() {
		System.out.println("Printing out properties for " + entry.getName());
		if(entry.isDirectory()) {
			System.out.println("This file is a directory.");
		}
		System.out.println("Path type:\t\t" + PathType.CLASS);
		System.out.println("Size: \t\t\t" + entry.getSize());
		System.out.println("Created: \t\t" + (entry.getCreationTime() != null ? formatDate(entry.getCreationTime()) : "N/A"));
		System.out.println("Modified: \t\t" + formatDate(entry.getLastModifiedTime()));
		System.out.println("Accessed: \t\t" + (entry.getLastAccessTime() != null ? formatDate(entry.getLastAccessTime()) : "N/A"));
	}
}
