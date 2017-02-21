package back_end;

import java.util.Scanner;

import commands.Command;
import commands.ParameterContainer;

public class RotationCommand implements Command{
	private int myParameter;

	/**
	 *  Sets the factor by which the turtle needs to rotate so that the Model can simply
	 *  call setRotate 
	 *  @param Scanner and string type are passed from StringInterpreter so that the 
	 *  remainder of the user-entered command can be parsed here
	 */
	@Override
	public void setParameters (Scanner scanner, String type) {
		if(type.equals("Right")){
			myParameter = scanner.nextInt();
		}
		else if(type.equals("Left")){
			myParameter = -scanner.nextInt();
		}
	}

	@Override
	public ParameterContainer getParameters() {
		// TODO Auto-generated method stub
		return null;
	}
}
