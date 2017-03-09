package back_end.commands.commandLibrary.turtle;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public abstract class TurtleCommand extends SimpleParameterCommand implements CommandInterface{

	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Iterator<Double> iter = getParameters().iterator();
		double returnValue = 0.0;
		
		while(iter.hasNext()){
			List<Double> inputs = createInputs(iter);
			returnValue += model.operateOnTurtle(applyInputs(model, inputs));
		}
		return returnValue;
	}
		
	private Function<Turtle, Double> applyInputs(Model model, List<Double> inputs) {
		BiFunction<Turtle, List<Double>, Double> action = supplyAction(model);
		return turtle -> action.apply(turtle, inputs);
	}


	protected abstract BiFunction<Turtle, List<Double>,Double> supplyAction(Model model);
	
	protected abstract List<Double> createInputs(Iterator<Double> iterator);

}
