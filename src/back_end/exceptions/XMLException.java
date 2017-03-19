package back_end.exceptions;

public class XMLException extends Exception{
    public XMLException(String msg){
    	super("XMLException: " + msg);
    }
}
