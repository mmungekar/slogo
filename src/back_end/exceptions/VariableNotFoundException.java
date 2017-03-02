package back_end.exceptions;

public class VariableNotFoundException extends Exception {
    public VariableNotFoundException(String text){
    	super("Variable Not Found: " + text);
    }
}
