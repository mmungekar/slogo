package back_end.model;

import back_end.Interface.CommandInterface;
import back_end.Interface.NodeIntermediateInteface;
import back_end.library.CommandLibrary;
import back_end.library.UnrecognizedCommandException;

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
	private String mSubContent;
	
    public Oxygen(String type){
    	mType = type;
    	mCommandLib = new CommandLibrary();
    }
    
    public void convertLight(String light) throws UnrecognizedCommandException{
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
    
    public void putSubContent(String content){
    	mSubContent = content;
    }
    
	
    public String getSubContent(){
    	return mSubContent;
    }
    
	@Override
	public T getContent() {
		return mContent;
		
	}
	
}
