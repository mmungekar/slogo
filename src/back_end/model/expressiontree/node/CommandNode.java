package back_end.model.expressiontree.node;

import java.util.ResourceBundle;

import back_end.commands.constant.Constant;
import back_end.exceptions.CommandException;
import back_end.exceptions.NotEnoughParameterException;
import back_end.exceptions.UnrecognizedCommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.libraries.CommandFactory;
import back_end.libraries.CustomCommandLibrary;
import back_end.model.container.Input;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.scene.Model;
import back_end.overhead.ProgramParser;

public class CommandNode extends TreeNode {
	public static final String PARAMETER_DIRECTORY = "resources/parameters/";
	private ResourceBundle presource;
	private CommandInterface mCommand;
	private CommandFactory mCommandLib;

	public CommandNode(Input x) throws CommandException {
		super(x);
		presource = ResourceBundle.getBundle(PARAMETER_DIRECTORY + "Parameter");

	}

	@Override
	public boolean isChildrenFull(CustomCommandLibrary ccl) {
		String formal = getName();
		if (this.getParent().getType().equals(Constant.GROUPSTART_TYPE))
			return false;
		if (hasDefaultCommand(formal)) {
			return this.getChildren().size() == getChildrenNum(formal);
		} else if (hasDefinedCommand(formal, ccl)) {
			System.out.println("Custom Defined Command Found: " + formal);
			try {
				return ccl.getCustomCommand(formal).getNumParams() == this.getChildren().size();
			} catch (UnrecognizedCommandException e) {
				return true;
			}
		}
		System.out.println("Command has not been defined: " + formal);
		return true;
	}

	private int getChildrenNum(String official) {
		return Integer.parseInt(presource.getString(official));
	}

	private boolean hasDefaultCommand(String command) {
		return presource.containsKey(command);
	}

	private boolean hasDefinedCommand(String command, CustomCommandLibrary ccl) {
		return ccl.containsKey(command);
	}

	@Override
	public double traverse(Model state) throws CommandException, VariableNotFoundException {
		mCommand = retrieveCommand(state);
		double retVal = mCommand.Execute(state);
		this.setValue(retVal);
		return retVal;
	}

	private CommandInterface retrieveCommand(Model state) throws CommandException, VariableNotFoundException {
		mCommandLib = new CommandFactory(state.getCurrentLanguage());
		CommandInterface Command = null;
		String formal = getName();
		try {
			Command = mCommandLib.getCommand(formal);
		} catch (CommandException e) {
			if (state.getCustomCommandLibrary().containsKey(formal)) {
				Command = state.getCustomCommandLibrary().get(formal);
			} else {
				throw new UnrecognizedCommandException(formal);
			}
		}
		System.out.println("Fetch new Command: " + formal);
		Command.setParameters(state,
				new ExpressionTree(this, state.getCurrentLanguage(), state.getCustomCommandLibrary()));
		return Command;

	}

}
