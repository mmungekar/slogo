package back_end.library;

import java.util.HashMap;

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
	
	public Double retrieveVariable(String name) throws VariableNotFoundException{
		if(!mVariables.containsKey(name))
			throw new VariableNotFoundException("No defined variable: " + name);
		return mVariables.get(name);
	}

}
