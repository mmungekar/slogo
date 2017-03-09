package back_end.commands.commandLibrary;

import java.util.List;
import java.util.function.BiFunction;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class BiFunctionCommand extends SimpleParameterCommand implements CommandInterface{
  
    public void checkParams() throws NotEnoughParameterException{
    	if((getParameterValues().size() % 2)!=0){
    		throw new NotEnoughParameterException(2);
    	}
    }
    
    @Override
	protected int getInputNumber(){
    	return 2;
    }

}
