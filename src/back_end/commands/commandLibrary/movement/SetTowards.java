package back_end.commands.commandLibrary.movement;

import java.util.Iterator;

import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class SetTowards extends TwoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
	    Double degrees  = (double) 0;
		Iterator<Double> iter = getParameterValue().iterator();
		while(iter.hasNext()){
			degrees+= model.operateOnTurtle(turtle -> turtle.setTowards(model.getHome().getX() + iter.next(), model.getHome().getY() - iter.next()));
		}
		return degrees;
	}

}