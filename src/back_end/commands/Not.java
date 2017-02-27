package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Not implements CommandInterface, Constant{
    private int mNot;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		mNot = a == 0 ? 1 : 0;
		
	}

	@Override
	public double Execute(ModelState state) { 
		return mNot;
	}

	@Override
	public int getParameterCount() {
		return NUM_NOT;
	}

}
