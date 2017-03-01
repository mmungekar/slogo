package back_end.commands.abstracts;

import back_end.commands.presetLibrary.CommandInterface;
import back_end.exceptions.NotEnoughParameterException;

public abstract class SingleParameterCommand implements CommandInterface{
	private double parameterValue;
	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		setParameterValue(ds[0]);
	}
	protected double getParameterValue() {
		return parameterValue;
	}
	private void setParameterValue(double parameterValue) {
		this.parameterValue = parameterValue;
	}

}
