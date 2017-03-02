package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class Quotient implements CommandInterface, Constant{
    private double mQuo;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mQuo = a / b;
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mQuo;
	}

}
