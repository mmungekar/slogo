package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class Minus implements CommandInterface<Double>, Constant{
    private double mInt;
	@Override
	public void setParameters(Double...ds) {
		double a = ds[0];
		mInt = -a;
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mInt;
	}


}
