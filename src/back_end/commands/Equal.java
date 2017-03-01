package back_end.commands;

import back_end.Input;
import back_end.Model;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Equal implements CommandInterface, Constant{
    private int mEqual;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mEqual = a == b ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mEqual;
	}

}