package back_end.commands.commandLibrary.math;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.commands.commandLibrary.TwoParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public class And extends TwoParameterCommand{

	@Override
	public double Execute(Model state) { 
		this.getParams();
		return (A != 0 && B != 0) ? 1 : 0;
	}


}
