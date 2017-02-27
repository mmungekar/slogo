package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Quotient implements CommandInterface, Constant{
    private double mQuo;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		double b = Double.parseDouble(input[1].getParameter());
		mQuo = a / b;
		
	}

	@Override
	public double Execute(ModelState state) {
		// TODO Auto-generated method stub
		return mQuo;
	}

	@Override
	public int getParameterCount() {
		return NUM_QUOTIENT;
	}

}