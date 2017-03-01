package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class Sum implements CommandInterface, Constant{
    private double mSum;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mSum = a + b;
		
	}

	@Override
	public double Execute(Model state) {
		return mSum;
	}

}
