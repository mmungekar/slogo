package back_end.commands.commandLibrary.movement;

import java.util.Iterator;

import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.node.TreeNode;
import back_end.model.scene.Model;
import javafx.geometry.Point2D;

public class SetPosition extends TwoParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) {

		Double distance  = (double) 0;
		Iterator<Double> iter = getParameterValue().iterator();
		while(iter.hasNext()){
			Point2D newPosition = new Point2D(model.getHome().getX() + iter.next(), model.getHome().getY() - iter.next());
			distance += model.operateOnTurtle(turtle -> turtle.setPosition(newPosition));
		}
		return distance;
	}
}