package back_end.commands;

import back_end.Input;
import back_end.Model;
import back_end.constant.Constant;

public class Sum implements CommandInterface, Constant{
    private double mSum;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mSum = a + b;
		
	}

	@Override
	public double Execute(Model state) {
		return mSum;
	}

}
