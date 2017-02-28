package commands;

import java.util.Scanner;

import back_end.Model;

public class RotationCommand implements CommandInterface {
	private ParameterContainer myParameter;
	private int parameterCount = 1;
	private int scale;

	public RotationCommand(boolean b) {
		myParameter = new MovementParameters();
		if (b) {
			scale = 1;
		} else {
			scale = -1;
		}
	}

	/**
	 * Sets the factor by which the turtle needs to rotate so that the Model can
	 * simply call setRotate
	 * 
	 * @param Scanner
	 *            and string type are passed from StringInterpreter so that the
	 *            remainder of the user-entered command can be parsed here
	 */
	@Override
	public void setParameters(double...ds) {
		((MovementParameters) myParameter).setMovementMagnitude(scale * ds[0]);
	}

	@Override
	public double Execute(Model state) {
		// TODO turtle ID
		state.setAngle(0, state.getAngle(0) + ((MovementParameters) myParameter).getMovementMagnitude());
		return ((MovementParameters) myParameter).getMovementMagnitude();
	}

	@Override
	public int getParameterCount() {
		return this.parameterCount;
	}
}
