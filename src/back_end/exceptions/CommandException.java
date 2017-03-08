package back_end.exceptions;

@SuppressWarnings("serial")
public abstract class CommandException extends Exception{

	public CommandException(String string) {
		super(string);
	}
	
	public CommandException(){
		super();
	}

}
