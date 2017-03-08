package back_end.commands.commandLibrary.movement;

import java.util.Iterator;

import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTreeNode;
import back_end.model.scene.Model;

public class SetPosition extends TwoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {
		Double distance  = (double) 0;
		Iterator<Double> iter = getParameterValue().iterator();
		while(iter.hasNext()){
		Double traveled =  model.setPos(model.getHome().getX() + iter.next(), 
				model.getHome().getY() - iter.next());
		distance+=traveled;
		}
		return distance;
	}
}
