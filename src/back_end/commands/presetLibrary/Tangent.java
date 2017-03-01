package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class Tangent implements CommandInterface, Constant{
    private double mTan;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mTan = Math.tan(Math.toRadians(a));
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mTan;
	}

}
