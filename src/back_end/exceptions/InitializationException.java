package back_end.exceptions;

@SuppressWarnings("serial")
public class InitializationException extends CommandException {

	public InitializationException(String command) {
		super("Cannot Initialize Command : " + command);
	}

}
