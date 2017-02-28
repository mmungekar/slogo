package commands;

import java.util.Scanner;

import back_end.Model;


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
	public double Execute(Model state) {
		moveForward(state, ((MovementParameters) parameters).getMovementMagnitude());
		return ((MovementParameters) parameters).getMovementMagnitude();
	}


	private void moveForward(Model model, double d) {
		double xDisplacement = calculateComponentMovementMagnitude(model.getAngle(0), d, X);
		double yDisplacement = calculateComponentMovementMagnitude(model.getAngle(0), d, Y);
		
		// TODO turtle ID
		model.setPos(0, model.getX(0) - xDisplacement, model.getY(0) - yDisplacement);

	}

	private double calculateComponentMovementMagnitude(double angle, double d, String string) {
		if (string.equals(X)) {
			return -1 * d * Math.sin(Math.toRadians(angle));
		} else if (string.equals(Y)) {
			return d * Math.cos(Math.toRadians(angle));
		}
		return d;
	}

	@Override
	public void setParameters(double...ds) {
		((MovementParameters) parameters).setMovementMagnitude(scale * (int)ds[0]);
	}


	@Override
	public int getParameterCount() {
		return this.parameterCount;
	}

}
