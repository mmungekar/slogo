package back_end.exceptions;

@SuppressWarnings("serial")
public class UnrecognizedTypeException extends CommandException {
	public UnrecognizedTypeException(String unrecognizedType) {
		super(String.format("Error: The Type was not Recognized\n  Unrecognized Type : %s",unrecognizedType));
	}	
	
}
