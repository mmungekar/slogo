package back_end.commands.commandLibrary.scene;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class PenUp extends SimpleParameterCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) {
		model.setPenUp();
		return 0;
	}
	

}
