package back_end.commands;

import back_end.Input;
import back_end.ModelState;
import back_end.constant.Constant;
import commands.CommandInterface;

public class NaturalLog implements CommandInterface, Constant{
    private double mLog;
	@Override
	public void setParameters(Input... input) {
		double a = Double.parseDouble(input[0].getParameter());
		mLog = Math.log(a);
		
	}

	@Override
	public double Execute(ModelState state) {
		// TODO Auto-generated method stub
		return mLog;
	}

	@Override
	public int getParameterCount() {
		return NUM_LOG;
	}

}
