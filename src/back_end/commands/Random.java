package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Random implements CommandInterface, Constant{
    private double mMax;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		mMax = a;
		
	}

	@Override
	public double Execute(ModelState state) {
		// TODO Auto-generated method stub
		return new java.util.Random().nextDouble() * mMax;
	}

	@Override
	public int getParameterCount() {
		return NUM_RANDOM;
	}

}
