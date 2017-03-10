package back_end.model.scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import back_end.commands.custom.CustomCommand;
import back_end.commands.custom.CustomVariable;
import back_end.libraries.CustomCommandLibrary;
import back_end.libraries.VariableLibrary;

public class CustomMaster {

	private VariableLibrary mGlobalVariableLibrary;
	private VariableLibrary mLocalVariableLibrary;
	private CustomCommandLibrary mCustomCommandLibrary;
	
	private Model myModel;

	public CustomMaster(Model model) {
		this.myModel = model;
		mGlobalVariableLibrary = new VariableLibrary();
		mLocalVariableLibrary = new VariableLibrary();
		mCustomCommandLibrary = new CustomCommandLibrary();
	}
	
	private void notifyModel(){
		myModel.setChangedAndNotifyObservers();
	}

	public void updateVariable(String name, double value) {
		mGlobalVariableLibrary.updateVariable(name, value);
		notifyModel();
	}

	public void addCustomCommand(String name, CustomCommand command) {
		mCustomCommandLibrary.put(name, command);
		notifyModel();
	}

	public void clearVariables() {
		mGlobalVariableLibrary = new VariableLibrary();
		mLocalVariableLibrary = new VariableLibrary();
		notifyModel();
	}

	public CustomCommandLibrary getCustomCommandLibrary() {
		return mCustomCommandLibrary;
	}

	public Collection<CustomVariable> getUserDefinedVariables() {
		List<CustomVariable> vars = new ArrayList<>();
		for (String key : mGlobalVariableLibrary.keySet()) {
			vars.add(mGlobalVariableLibrary.get(key));

		}
		return vars;
	}

	public Collection<String> getUserDefinedCommands() {
		return mCustomCommandLibrary.keySet();
	}

	public boolean hasCustomVariable(String parameter) {
		return mLocalVariableLibrary.hasVariable(parameter);
	}

	public void clearCommands() {
		mCustomCommandLibrary = new CustomCommandLibrary();
	}

	public boolean isVariableStored(String parameter) {
		return this.mGlobalVariableLibrary.hasVariable(parameter) || this.mLocalVariableLibrary.hasVariable(parameter);
	}

	public VariableLibrary getLocalVariableLibrary() {
		return this.mLocalVariableLibrary;
	}

	public void setLocalVariableLibrary(VariableLibrary mCustomVarLib) {
		this.mLocalVariableLibrary = mCustomVarLib;
	}

	public Double retrieveVariable(String nodeName) {
		if (this.mGlobalVariableLibrary.hasVariable(nodeName)) {
			return this.mGlobalVariableLibrary.get(nodeName).getValue();
		} else if (this.mLocalVariableLibrary.hasVariable(nodeName)) {
			return this.mLocalVariableLibrary.get(nodeName).getValue();
		}
		return 0d;
	}

}
