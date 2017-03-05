package back_end.libraries;

import java.util.HashMap;

import back_end.commands.commandLibrary.userdefined.CustomCommandPair;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.model.expressiontree.ExpressionTree;

public class CustomCommandLibrary extends HashMap<String, CustomCommandPair>{
	
	public void setCustomCommand(String command, ExpressionTree tree, VariableLibrary varLib){
		CustomCommandPair pair = new CustomCommandPair(varLib, tree);
		if(this.containsKey(command))
			this.remove(command);
		this.put(command, pair);
	}
	
	public void clear(){
		this.clear();
	}
	
	public CustomCommandPair getCustomCommand(String command) throws UnrecognizedCommandException{
		if(this.containsKey(command))
			return this.get(command);
		throw new UnrecognizedCommandException("Cannot find the user defined command: " + command);
	}

}
