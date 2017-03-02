package back_end.Interface;

import back_end.constant.Constant;

public interface NodeIntermediateInteface<T> extends Constant{
    
	public void putContent(T content);
	
	public T getContent();
    
}
