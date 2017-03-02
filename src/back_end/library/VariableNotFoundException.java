package back_end.library;

public class VariableNotFoundException extends Exception {
    public VariableNotFoundException(String text){
    	super("Variable Not Found: " + text);
    }
}
