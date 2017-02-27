package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class NotEqual implements CommandInterface, Constant{
    private int mNotEqual;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		double b = Double.parseDouble(input[1].getParameter());
		mNotEqual = a != b ? 1 : 0;
		
	}

	@Override
	public double Execute(ModelState state) { 
		return mNotEqual;
	}

	@Override
	public int getParameterCount() {
		return NUM_NOTEQUAL;
	}

}
