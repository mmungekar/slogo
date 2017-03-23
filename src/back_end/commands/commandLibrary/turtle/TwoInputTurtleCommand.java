package back_end.commands.commandLibrary.turtle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;

public abstract class TwoInputTurtleCommand extends TurtleCommand implements CommandInterface{
	
	public void checkParams() throws NotEnoughParameterException{
		if ((getParameters().size() % 2) != 0) {
			throw new NotEnoughParameterException(2);
		}
	}


	@Override
	protected List<Double> createInputs(Iterator<Double> iterator) {
		double param1 = iterator.next();
		double param2 = iterator.next();
		List<Double>inputs = new ArrayList<Double>();
		inputs.add(param1);
		inputs.add(param2);
		return inputs;
	}
	
	

}
