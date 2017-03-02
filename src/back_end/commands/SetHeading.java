package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class SetHeading implements CommandInterface<Double>{
    private double angle;
	@Override
	public void setParameters(Double... ds) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		angle = ds[0];
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
	    model.setAngle(0, angle);
	    return angle;
	}

}
