package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.Constant;
import back_end.model.Model;

public class Reminder implements CommandInterface, Constant{
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
