package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class Sine implements CommandInterface, Constant{
    private double mSin;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mSin = Math.sin(Math.toRadians(a));
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mSin;
	}

}
