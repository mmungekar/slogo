package back_end.commands.math;

import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

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
