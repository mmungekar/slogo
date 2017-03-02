package back_end.exceptions;

@SuppressWarnings("serial")
public class UnrecognizedCommandException extends CommandException {
	public UnrecognizedCommandException(String unrecognizedCommand) {
		super(String.format("Error: A Command was not Recognized\n  Unrecognized Command : %s",unrecognizedCommand));
	}	
	
}
