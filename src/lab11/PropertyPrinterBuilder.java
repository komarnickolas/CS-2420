package lab11;

import java.io.File;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lab11.PropertyPrinter.PathType;
/**
 * This is a wrapper class to construct the proper PropertyPrinter object, depending on the
 * type of file supplied
 * @author ryans
 */
public class PropertyPrinterBuilder {
	public static PropertyPrinter makePrinterFor(String path) {
		File file = new File(path); // look on the file system 
		if(!file.exists()) {
			// This is where the magic happens. We use the JVM's class loader to find the resource...it makes our 
			// path relative to the classpath, instead of the root of the file system!
			URL url = PropertyPrinterBuilder.class.getClassLoader().getResource(path); // look on the classpath
			
			if(url == null ) { // Still can't find it. Bummer!
				throw new LabElevenException("File not found: " + path);
			}
			
			String protocol = url.getProtocol();
			if(protocol.startsWith("jar") || protocol.startsWith("zip")) {
				return new ZipEntryPrinter(url); 
			} else if(protocol.startsWith("file")) {
				return new FilePropertyPrinter(new File(url.getPath()), PathType.CLASS);
			} else {
				throw new LabElevenException("Unsupported FilePrinter protocol: " + protocol);
			}
		}
		return new FilePropertyPrinter(file, determinPathType(path));
	}
	
	// Just a little logic to see if you are supplying an Absolute path or a Relative path.
	private static PathType determinPathType(String path) {
		boolean isWindows = System.getProperty("os.name").contains("Window"); 
		if(path.startsWith("/")) {
			if(isWindows) {
				throw new LabElevenException("You've given me a unix absolute path...but you're running on windows!");
			}
			return PathType.ABSOLUTE;
		} else if(isWindowsAbsolutePath(path)) {
			if(!isWindows) {
				throw new LabElevenException("You've given me a Windows absolute path...but you're running a flavor of Unix!");
			}
			return PathType.ABSOLUTE;
		} else {
			return PathType.RELATIVE;
		}
	}

	private static boolean isWindowsAbsolutePath(String path) {
		Pattern pattern = Pattern.compile("[a-zA-Z]+:\\\\"); // regex for [a-zA-Z]+:\\
		Matcher matcher = pattern.matcher(path);
		if(matcher.find()) {
			return matcher.start() == 0;
		}
		return false;
	}
}
