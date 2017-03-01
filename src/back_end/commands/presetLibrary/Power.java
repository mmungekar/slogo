package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

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
