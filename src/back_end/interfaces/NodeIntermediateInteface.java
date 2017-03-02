package back_end.interfaces;

import back_end.commands.constant.Constant;

public interface NodeIntermediateInteface<T> extends Constant{
    
	public void putContent(T content);
	
	public T getContent();
    
}
