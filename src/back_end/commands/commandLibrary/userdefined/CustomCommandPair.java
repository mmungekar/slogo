package back_end.commands.commandLibrary.userdefined;

import back_end.libraries.VariableLibrary;
import back_end.model.container.Pair;
import back_end.model.expressiontree.ExpressionTree;

public class CustomCommandPair extends Pair<VariableLibrary, ExpressionTree>{

	public CustomCommandPair(VariableLibrary varLib, ExpressionTree tree) {
		super(varLib, tree);
		
	}

}
