package back_end.commands;

import back_end.Input;
import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class Cosine implements CommandInterface<Double>, Constant{
    private double mCos;
	@Override
	public void setParameters(Double...ds) {
		double a = ds[0];
		mCos = Math.cos(a);
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mCos;
	}

}
