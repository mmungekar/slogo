package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Sine implements CommandInterface, Constant{
    private double mSin;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		mSin = Math.sin(a);
		
	}

	@Override
	public double Execute(ModelState state) {
		// TODO Auto-generated method stub
		return mSin;
	}

	@Override
	public int getParameterCount() {
		return NUM_SIN;
	}

}
