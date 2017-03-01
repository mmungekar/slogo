package back_end.exceptions;

@SuppressWarnings("serial")
public class NotEnoughParameterException extends CommandException {
	
	public NotEnoughParameterException(String incompleteCommand) {
		super("Not enough parameters for the function : " + incompleteCommand);
	}	
	
}
