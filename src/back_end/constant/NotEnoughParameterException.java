package back_end.constant;

public class NotEnoughParameterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NotEnoughParameterException(String incompleteCommand) {
		super("Not enough parameters for the function : " + incompleteCommand);
	}	
	
}
