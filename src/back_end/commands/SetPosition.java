package back_end.commands;

import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public class SetPosition implements CommandInterface{
    private double x;
    private double y;
	@Override
	public void setParameters(double... ds) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		x = ds[0];
		y = ds[1];
	}

	@Override
	public double Execute(Model model) {
		// TODO Auto-generated method stub
		double ox = model.getX(0);
		double oy = model.getY(0);
		double distance = Math.pow((Math.pow((ox-x), 2) + Math.pow((oy-y), 2)) , 1/2d);
		model.setPos(0, x, y);
		return distance;
	}

}
