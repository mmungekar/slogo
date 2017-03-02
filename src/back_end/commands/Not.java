package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class Not implements CommandInterface<Double>, Constant{
    private int mNot;
	@Override
	public void setParameters(Double...ds) {
		double a = ds[0];
		mNot = a == 0 ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mNot;
	}

}
