package back_end.commands.commandLibrary.movement;

import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class SetTowards extends TwoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		this.getParams();
		System.out.println(model.getHome());
	    return model.operateOnTurtle(turtle -> turtle.setTowards(model.getHome().getX() + A, model.getHome().getY() - B));
	}

}
