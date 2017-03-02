package back_end.libraries;

import java.util.HashMap;

import back_end.exceptions.VariableNotFoundException;

public class VariableLibrary {
	private HashMap<String, Double> mVariables;
	
	public VariableLibrary(){
		mVariables = new HashMap<>();
	}
	
	public boolean hasVariable(String name){
	    return mVariables.containsKey(name);	
	}
	
	public void insertVariable(String name, Double value){
		if(name.isEmpty())
			return;
		mVariables.put(name, value);
	}
	
	public void updateVariable(String name, Double value){
		if(mVariables.containsKey(name)){
			mVariables.remove(name);
		}
		insertVariable(name, value);
	}
	
	public Double retrieveVariable(String name) throws VariableNotFoundException{
		if(!mVariables.containsKey(name))
			throw new VariableNotFoundException(name);
		return mVariables.get(name);
	}

}
