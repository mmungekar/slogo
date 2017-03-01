package back_end.commands;

import back_end.Model;
import back_end.constant.Constant;

public class Random implements CommandInterface, Constant{
    private double mMax;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mMax = a;
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return new java.util.Random().nextDouble() * mMax;
	}

}
