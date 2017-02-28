package back_end.commands.math;

import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

public class Sine implements CommandInterface, Constant{
    private double mSin;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mSin = Math.sin(a);
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mSin;
	}

}
