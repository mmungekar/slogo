package back_end.commands.math;

import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

public class Tangent implements CommandInterface, Constant{
    private double mTan;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mTan = Math.tan(a);
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mTan;
	}

}
