package back_end.commands.commandLibrary;

import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public abstract class TwoParameterCommand extends SimpleParameterCommand implements CommandInterface{
    protected double A;
    protected double B;
    
    protected void getParams(){
    	A = this.getParameterValue().get(0);
		B = this.getParameterValue().get(1);
    }


}
