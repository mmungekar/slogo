package commands;

public class MovementParameters implements ParameterContainer {
	private double magnitude;

	public MovementParameters() {
		magnitude = 0;
	}

	public void setMovementMagnitude(double in) {
		magnitude = in;
	}

	public double getMovementMagnitude() {
		return magnitude;
	}
}
