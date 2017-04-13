package lab11;

public class LabElevenException extends RuntimeException {

	/**
	 *  Because Exceptions are Java Serizalizable, they like to have an "ID" so you
	 *  know if you're seralizing the current version of the Exception.
	 *  
	 *  This is a wrapper class for all the Checked exceptions that Java is forcing me to write.
	 */
	private static final long serialVersionUID = -1006481888135747521L;
	
	public LabElevenException(Throwable cause) {
		super(cause);
	}
	
	public LabElevenException(String message) {
		super(message);
	}

}
