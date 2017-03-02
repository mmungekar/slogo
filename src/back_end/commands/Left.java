package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class Left implements CommandInterface<Double>{
    private double angle;
	@Override
	public void setParameters(Double... ds) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		angle = ds[0];
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
	    double a = model.getAngle(0);
	    model.setAngle(0, a + angle);
	    return angle;
	}

}
