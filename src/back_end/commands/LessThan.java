package back_end.commands;

import back_end.Input;
import back_end.Model;
import back_end.constant.Constant;

public class LessThan implements CommandInterface, Constant{
    private int mLess;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mLess = a < b ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mLess;
	}

}
