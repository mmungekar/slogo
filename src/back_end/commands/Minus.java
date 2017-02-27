package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Minus implements CommandInterface, Constant{
    private double mInt;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		mInt = -a;
		
	}

	@Override
	public double Execute(ModelState state) {
		// TODO Auto-generated method stub
		return mInt;
	}

	@Override
	public int getParameterCount() {
		return NUM_MINUS;
	}

}
