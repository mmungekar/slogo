package back_end;

import java.util.Scanner;

import commands.Command;
import commands.ParameterContainer;

public class MovementCommand implements Command {
	private String myType;
	private int myParameter;

	@Override
	public void setParameters(Scanner scanner, String type) {
		// This really depends on how the Model needs to be fed this information
		// because all motion is relative to the turtle's direction, which the controller 
		//doesn't know
		myType = type;
		myParameter = scanner.nextInt();

	}

	@Override
	public ParameterContainer getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
