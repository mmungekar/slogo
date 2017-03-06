package back_end.commands.commandLibrary.math;

import back_end.commands.commandLibrary.PresetCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.expressiontree.ExpressionTree;
import back_end.model.expressiontree.Oxygen;
import back_end.model.scene.Model;

public abstract class TwoParamMath extends PresetCommand implements CommandInterface{
    protected double A;
    protected double B;
    
    protected void getParams(){
    	A = this.getParameterValue()[0];
		B = this.getParameterValue()[1];
    }


}
