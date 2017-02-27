package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class ArcTangent implements CommandInterface, Constant{
    private double mAtan;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		mAtan = Math.atan(a);
		
	}

	@Override
	public double Execute(ModelState state) {
		// TODO Auto-generated method stub
		return mAtan;
	}

	@Override
	public int getParameterCount() {
		return NUM_ATAN;
	}

}
