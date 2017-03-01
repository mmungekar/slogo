package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class Difference implements CommandInterface, Constant{
    private double mDiff;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mDiff = a + b;
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mDiff;
	}

}
