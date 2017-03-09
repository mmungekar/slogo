package back_end.model.expressiontree;

import back_end.interfaces.CommandInterface;
import back_end.interfaces.NodeIntermediateInteface;
import back_end.libraries.CommandFactory;
import back_end.exceptions.CommandException;
import back_end.exceptions.UnrecognizedCommandException;

/**
 * Oxygen holds the content of a tree node as well as the type It acts as an
 * intermediate product between tree nodes which can execute the current command
 * and pass information upwards in a flexible and packaged styles.
 * 
 * @author Feng
 *
 * @param <T>
 */
public class Oxygen<T> implements NodeIntermediateInteface<T> {
	private String mType;

	private T mContent;
	private String mSubContent;
	private double retVal;

	public Oxygen(String language, String type) {
		mType = type;
	}

	public void convertLight(String light) throws CommandException {
		switch (mType) {
		case ROOT_TYPE:
			break;
		case CONSTANT_TYPE:
			Double d = Double.parseDouble(light);
			putContent((T) d);
			putReturnValue(d);
			break;
		case VARIABLE_TYPE:
			String s = light;
			putContent((T) s);
			break;
		case COMMAND_TYPE:
			//CommandInterface ci = mCommandLib.getCommand(light);
			String ci = light;
			putContent((T) ci);
			break;
		}
	}

	@Override
	public void putContent(T content) {
		mContent = content;
	}

	@Override
	public T getContent() {
		return mContent;

	}

	public void putSubContent(String content) {
		mSubContent = content;
	}

	public String getSubContent() {
		return mSubContent;
	}
	
	public void putReturnValue(Double value) {
		retVal = value;
	}

	public Double getReturnValue() {
		return retVal;
	}

}
