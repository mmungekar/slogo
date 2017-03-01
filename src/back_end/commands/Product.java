package back_end.commands;

import back_end.Model;
import back_end.constant.Constant;

public class Product implements CommandInterface, Constant{
    private double mProd;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mProd = a * b;
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mProd;
	}

}
