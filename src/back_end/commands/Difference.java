package back_end.commands;

import back_end.Input;
import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class Difference implements CommandInterface<Double>, Constant{
    private double mDiff;
	@Override
	public void setParameters(Double...ds) {
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
