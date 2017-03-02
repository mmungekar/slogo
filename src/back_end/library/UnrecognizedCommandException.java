package back_end.library;

public class UnrecognizedCommandException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public UnrecognizedCommandException(String unrecognizedCommand) {
		super("Unrecognized Command : " + unrecognizedCommand);
	}	
	
}
