package back_end.commands;

import back_end.Model;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Or implements CommandInterface, Constant{
    private int mOr;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mOr = a == b ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mOr;
	}


}