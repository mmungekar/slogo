package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Sum implements CommandInterface, Constant{
    private double mSum;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		double b = Double.parseDouble(input[1].getParameter());
		mSum = a + b;
		
	}

	@Override
	public double Execute(ModelState state) {
		return mSum;
	}

	@Override
	public int getParameterCount() {
		return NUM_SUM;
	}

}
