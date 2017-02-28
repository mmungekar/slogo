package back_end.commands.turtle;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commands.CommandInterface;

public class SetHeading implements CommandInterface{
    private double angle;
	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
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
