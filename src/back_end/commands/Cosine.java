package back_end.commands;

import back_end.Model;
import back_end.constant.Constant;

public class Cosine implements CommandInterface, Constant{
    private double mCos;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mCos = Math.cos(Math.toRadians(a));
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mCos;
	}

}
