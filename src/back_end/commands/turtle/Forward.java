package back_end.commands.turtle;

import back_end.Model;
import back_end.NotEnoughParameterException;
import back_end.commands.CommandInterface;

public class Forward implements CommandInterface{
    private double mMagnitude;
	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		mMagnitude = ds[0];
	}

	@Override
	public double Execute(Model model) {
		double angle = model.getAngle(0);
		double dx = Math.cos(Math.toRadians(angle)) * mMagnitude;
		double dy = Math.sin(Math.toRadians(angle)) * mMagnitude;
		double x = model.getX(0);
		double y = model.getY(0);
		model.setPos(0, x + dx, y + dy);
		return mMagnitude;
	}

}


