package back_end.commands.commandLibrary;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;

public abstract class TwoInputCommand extends DistributiveParameterCommand implements CommandInterface{
  
    private static final int INPUT_NUMBER = 2;

	public void checkParams() throws NotEnoughParameterException{
    	if((getParameterValues().size() % 2)!=0){
    		throw new NotEnoughParameterException(2);
    	}
    }
    
    @Override
	protected int getInputNumber(){
    	return INPUT_NUMBER;
    }

}
