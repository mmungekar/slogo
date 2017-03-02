package back_end.model;

import back_end.interfaces.CommandInterface;
import back_end.interfaces.NodeIntermediateInteface;
import back_end.libraries.CommandLibrary;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;

/**
 * Oxygen holds the content of a tree node as well as the type
 * It acts as an intermediate product between tree nodes which can
 * execute the current command and pass information upwards in a 
 * flexible and packaged styles. 
 * @author Feng
 *
 * @param <T>
 */
public class Oxygen<T> implements NodeIntermediateInteface<T>{
    private String mType;
    private CommandLibrary mCommandLib;
    
	private T mContent;
	
    public Oxygen(String language, String type){
    	mType = type;
    	mCommandLib = new CommandLibrary(language);
    }
    
    public void convertLight(String light) throws CommandException{
    	switch (mType) {
    	case ROOT_TYPE:
    		break;
		case CONSTANT_TYPE:
			Double d = Double.parseDouble(light);
			putContent((T) d);
			break;
		case VARIABLE_TYPE:
			String s = light;
			putContent((T) s);
			break;
		case COMMAND_TYPE:
			CommandInterface ci = mCommandLib.getCommand(light);
			putContent((T) ci);
			break;
		}
    }
    
    @Override
    public void putContent(T content){
    	mContent = content;
    }
    
	
	@Override
	public T getContent() {
		return mContent;
		
	}
	
}
