package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class Backward implements CommandInterface<Double> {
    private double mMagnitude;
	@Override
	public void setParameters(Double... ds) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		mMagnitude = ds[0];
	}

	@Override
	public double Execute(Model model) {
		double angle = model.getAngle(0);
		double dx = Math.cos(Math.toRadians(-angle)) * mMagnitude;
		double dy = - Math.sin(Math.toRadians(-angle)) * mMagnitude;
		double x = model.getX(0);
		double y = model.getY(0);
		model.setPos(0, x + dx, y + dy);
		return mMagnitude;
	}

}


