package back_end.exceptions;

@SuppressWarnings("serial")
public class NotEnoughParameterException extends CommandException {

	public NotEnoughParameterException(String incompleteCommand, int expected, int inputted) {
		super(String.format(
				"Error: Incorrect Number of Parameters\n Command : %1$s \n   Expected #: %2$d \n   Inputted #: %3$d",
				incompleteCommand, expected, inputted));
	}

}
