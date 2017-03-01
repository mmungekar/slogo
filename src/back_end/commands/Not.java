package back_end.commands;

import back_end.Model;
import back_end.constant.Constant;
import commands.CommandInterface;

public class Not implements CommandInterface, Constant{
    private int mNot;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mNot = a == 0 ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mNot;
	}

}
