package back_end.commands.math;

import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

public class Remainder implements CommandInterface, Constant{
    private int mRemind;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mRemind = (int) (a % b);
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mRemind;
	}

}
