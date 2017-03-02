package back_end;

public class NotEnoughParameterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NotEnoughParameterException(String incompleteCommand) {
		super("Not enough parameters for the function : " + incompleteCommand);
	}	
	
}
