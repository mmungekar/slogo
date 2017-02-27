package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class GreaterThan implements CommandInterface, Constant{
    private int mGreater;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		double b = Double.parseDouble(input[1].getParameter());
		mGreater = a > b ? 1 : 0;
		
	}

	@Override
	public double Execute(ModelState state) { 
		return mGreater;
	}

	@Override
	public int getParameterCount() {
		return NUM_GREATER;
	}

}
