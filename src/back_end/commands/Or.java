package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class Or implements CommandInterface<Double>, Constant{
    private int mOr;
	@Override
	public void setParameters(Double...ds) {
		double a = ds[0];
		double b = ds[1];
		mOr = a == b ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mOr;
	}


}
