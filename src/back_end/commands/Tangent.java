package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Tangent implements CommandInterface, Constant{
    private double mTan;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		mTan = Math.tan(a);
		
	}

	@Override
	public double Execute(ModelState state) {
		// TODO Auto-generated method stub
		return mTan;
	}

	@Override
	public int getParameterCount() {
		return NUM_TAN;
	}

}
