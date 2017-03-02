package back_end.commands;

import back_end.Input;
import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

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
