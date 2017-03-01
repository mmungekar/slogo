package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class Random implements CommandInterface, Constant{
    private double mMax;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mMax = a;
		
	}

	@Override
	public double Execute(Model state) {
		return new java.util.Random().nextDouble() * mMax;
	}

}
