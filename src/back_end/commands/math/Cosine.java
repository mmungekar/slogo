package back_end.commands.math;

import back_end.Input;
import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

public class Cosine implements CommandInterface, Constant{
    private double mCos;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mCos = Math.cos(a);
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mCos;
	}

}