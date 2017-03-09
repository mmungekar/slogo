package back_end.commands.commandLibrary.turtle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import back_end.interfaces.CommandInterface;

public abstract class OneInputTurtleCommand extends TurtleCommand implements CommandInterface{

	@Override
	protected List<Double> createInputs(Iterator<Double> iterator) {
		double param1 = iterator.next();
		List<Double>inputs = new ArrayList<Double>();
		inputs.add(param1);
		return inputs;
	}
}
