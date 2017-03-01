package back_end.exceptions;

@SuppressWarnings("serial")
public class UnrecognizedCommandException extends CommandException {
	public UnrecognizedCommandException(String unrecognizedCommand) {
		super("Unrecognized Command : " + unrecognizedCommand);
	}	
	
}
