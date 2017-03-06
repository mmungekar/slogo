package back_end.libraries;

import java.util.LinkedHashMap;

import back_end.commands.custom.CustomVariable;
import back_end.exceptions.VariableNotFoundException;

public class VariableLibrary extends LinkedHashMap<String, Double>{	
	
	public boolean hasVariable(String name){
	    return this.containsKey(name);	
	}
	
	public void insertVariable(String name, Double value){
		if(name.isEmpty())
			return;
		this.put(name, value);
	}
	
	public void updateVariable(String name, Double value){
		if(this.containsKey(name)){
			this.remove(name);
		}
		insertVariable(name, value);
	}
	
	public Double retrieveVariable(String name) throws VariableNotFoundException{
		if(!this.containsKey(name))
			throw new VariableNotFoundException(name);
		return this.get(name);
	}
}
