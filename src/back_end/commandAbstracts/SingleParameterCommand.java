package back_end.commandAbstracts;

import back_end.NotEnoughParameterException;
import back_end.commands.CommandInterface;

public abstract class SingleParameterCommand implements CommandInterface{
	private double parameterValue;
	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		setParameterValue(ds[0]);
	}
	protected double getParameterValue() {
		return parameterValue;
	}
	private void setParameterValue(double parameterValue) {
		this.parameterValue = parameterValue;
	}

}
