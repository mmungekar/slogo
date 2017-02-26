package commands;

public class ParameterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ParameterException(String command, Integer expected, Integer received) {
		super(String.format("Incorrect Number of Parameters for command: %1$s, Expected: %2$d, Received: %3$d", command, expected, received));
	}	
}
