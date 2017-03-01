package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class And implements CommandInterface, Constant{
    private int mAnd;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		double b = ds[1];
		mAnd = (a != 0 && b != 0) ? 1 : 0;
		
	}

	@Override
	public double Execute(Model state) { 
		return mAnd;
	}


}
