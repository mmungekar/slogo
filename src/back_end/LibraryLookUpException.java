package back_end;

public class LibraryLookUpException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public LibraryLookUpException(String command) {
		super(String.format("Could not find command (%s) in command library", command));
	}	
}
