package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class NotEqual implements CommandInterface, Constant{
    private int mNotEqual;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mNotEqual = a != b ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mNotEqual;
	}

}
