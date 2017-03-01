package back_end.commands.presetLibrary;

import back_end.Model;
import back_end.exceptions.NotEnoughParameterException;
import javafx.geometry.Point2D;

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
	    double dx = (model.getHome().getX() + ox) - model.getX(0);
	    double dy = (model.getHome().getY() - oy) - model.getY(0);
	    
	    double prevAngle = model.getAngle(0);
	    angle = Math.toDegrees(Math.atan(dy / dx));
	    model.setAngle(0, angle);
	    return angle-prevAngle;
	}

}
