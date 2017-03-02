package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class SetTowards implements CommandInterface{
    private double angle;
    private double ox;
    private double oy;
	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		ox = ds[0];
		oy = ds[1];
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
	    double dx = ox - model.getX(0);
	    double dy = oy - model.getY(0);
	    double prevangle = model.getAngle(0);
	    angle = Math.atan(dy / dx);
	    model.setAngle(0, angle);
	    return angle-prevangle;
	}

}
