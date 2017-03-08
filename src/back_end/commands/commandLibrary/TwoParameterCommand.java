package back_end.commands.commandLibrary;

import back_end.commands.constant.Constant;
import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public abstract class TwoParameterCommand extends SimpleParameterCommand implements CommandInterface{
  
    public void checkParams() throws NotEnoughParameterException{
    	if((getParameterValue().size() % 2)!=0){
    		throw new NotEnoughParameterException(2);
    	}
    }


}
