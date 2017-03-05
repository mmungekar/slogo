package back_end.libraries;

import java.util.HashMap;

import back_end.commands.CustomCommand;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.model.container.Pair;
import back_end.model.expressiontree.ExpressionTree;

public class CustomCommandLibrary extends HashMap<String, CustomCommand>{
	
	public void setCustomVarTreePair(String name, CustomCommand command){
		if(this.containsKey(name))
			this.remove(name);
		this.put(name, command);
	}
	
	public void clear(){
		this.clear();
	}
	
	public CustomCommand getCustomCommand(String command) throws UnrecognizedCommandException{
		if(this.containsKey(command))
			return this.get(command);
		throw new UnrecognizedCommandException("Cannot find the user defined command: " + command);
	}

}
