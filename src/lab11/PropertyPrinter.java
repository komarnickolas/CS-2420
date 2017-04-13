package lab11;

import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * I'm trying to work in a little OOP principles to this lab. I make a PropertyPrinter interface
 * so I can take advantage of polymorphism, and not worry if the File really exists, or if it is 
 * zipped up on the class path. 
 * @author ryans
 */
public interface PropertyPrinter {
	
	public enum PathType {
		ABSOLUTE, RELATIVE, CLASS
	}
	
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy-HH:mm:ss");

	void print();
	
	default String formatDate(FileTime ft) {
		return  SIMPLE_DATE_FORMAT.format(new Date(ft.toMillis()));
	}
}

