package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class NotEqual implements CommandInterface<Double>, Constant{
    private int mNotEqual;
	@Override
	public void setParameters(Double...ds) {
		double a = ds[0];
		double b = ds[1];
		mNotEqual = a != b ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mNotEqual;
	}

}
