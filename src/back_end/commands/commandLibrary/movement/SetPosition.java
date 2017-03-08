package back_end.commands.commandLibrary.movement;

import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import javafx.geometry.Point2D;

public class SetPosition extends TwoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		this.getParams();
		Point2D newPosition = new Point2D(model.getHome().getX() + A, model.getHome().getY() - B);
		return model.operateOnTurtle(turtle -> turtle.setPosition(newPosition));
	}

}
