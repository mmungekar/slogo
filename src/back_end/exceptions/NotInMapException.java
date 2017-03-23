package back_end.exceptions;

@SuppressWarnings("serial")
public class NotInMapException extends Exception {
	public NotInMapException(String notFoundValue) {
		super(String.format("Error: The Value %s was not found in its corresponding map", notFoundValue));
	}
}
