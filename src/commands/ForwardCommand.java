package commands;

import java.util.Scanner;

import back_end.ModelState;

public class ForwardCommand implements CommandInterface {
	public static final String X = "X";
	public static final String Y = "Y";

	private ParameterContainer parameters;
	private int parameterCount = 1;
	private int scale;

	public ForwardCommand(boolean b) {
		parameters = new MovementParameters();
		if (b) {
			scale = 1;
		} else {
			scale = -1;
		}

	}

	@Override
	public void Execute(ModelState state) {
		moveForward(state, ((MovementParameters) parameters).getMovementMagnitude());
	}

	private void moveForward(ModelState state, int movementMagnitude) {
		double xDisplacement = calculateComponentMovementMagnitude(state.getAngle(0), movementMagnitude, X);
		double yDisplacement = calculateComponentMovementMagnitude(state.getAngle(0), movementMagnitude, Y);

		// TODO turtle ID
		state.setPos(0, state.getX(0) - xDisplacement, state.getY(0) - yDisplacement);

	}

	private double calculateComponentMovementMagnitude(double angle, int movementMagnitude, String string) {
		if (string.equals(X)) {
			return -1 * movementMagnitude * Math.sin(Math.toRadians(angle));
		} else if (string.equals(Y)) {
			return movementMagnitude * Math.cos(Math.toRadians(angle));
		}
		return movementMagnitude;
	}

	@Override
	public void setParameters(String nextLine) {
		Scanner scanner = new Scanner(nextLine);
		((MovementParameters) parameters).setMovementMagnitude(scale * scanner.nextInt());
		scanner.close();
	}

	@Override
	public int getParameterCount() {
		return this.parameterCount;
	}

}
