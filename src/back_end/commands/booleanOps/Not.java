package back_end.commands.booleanOps;

import back_end.Model;
import back_end.commands.CommandInterface;
import back_end.constant.Constant;

public class Not implements CommandInterface, Constant{
    private int mNot;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mNot = a == 0 ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mNot;
	}

}
