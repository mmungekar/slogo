package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class And implements CommandInterface, Constant{
    private int mAnd;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		double b = Double.parseDouble(input[1].getParameter());
		mAnd = (a != 0 && b != 0) ? 1 : 0;
		
	}

	@Override
	public double Execute(ModelState state) { 
		return mAnd;
	}

	@Override
	public int getParameterCount() {
		return NUM_AND;
	}

}
