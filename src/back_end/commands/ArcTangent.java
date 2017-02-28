package back_end.commands;

import back_end.Input;
import back_end.Model;
import back_end.constant.Constant;

public class ArcTangent implements CommandInterface, Constant{
    private double mAtan;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mAtan = Math.atan(a);
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mAtan;
	}


}
