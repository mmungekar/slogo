package back_end.commands.math;

import back_end.Input;
import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

public class Power implements CommandInterface, Constant{
    private double mPow;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mPow = Math.pow(a, b);
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mPow;
	}

}
