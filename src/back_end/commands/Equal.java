package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Equal implements CommandInterface, Constant{
    private int mEqual;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		double b = Double.parseDouble(input[1].getParameter());
		mEqual = a == b ? 1 : 0;
		
	}

	@Override
	public double Execute(ModelState state) { 
		return mEqual;
	}

	@Override
	public int getParameterCount() {
		return NUM_EQUAL;
	}

}
