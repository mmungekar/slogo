package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.commands.constant.Constant;

public class ArcTangent implements CommandInterface, Constant{
    private double mAtan;
	@Override
	public void setParameters(double...ds) {
		double a = ds[0];
		mAtan = Math.toDegrees(Math.atan(a));
		
	}

	@Override
	public double Execute(Model state) {
		// TODO Auto-generated method stub
		return mAtan;
	}


}
