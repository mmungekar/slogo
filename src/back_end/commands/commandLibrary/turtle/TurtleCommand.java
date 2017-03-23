// This entire file is part of my masterpiece.
// MIGUEL ANDERSON

package back_end.commands.commandLibrary.turtle;

import java.util.ArrayList;
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
/**
 * By Miguel Anderson
 */

public abstract class TurtleCommand extends SimpleParameterCommand implements CommandInterface{

	
	/* (non-Javadoc)
	 * @see back_end.commands.commandLibrary.SimpleParameterCommand#Execute(back_end.model.scene.Model)
	 * 
	 * For turtle commands, we extract an iterator of parameters. Then we 
	 * 
	 * 
	 */
	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Iterator<Double> iter = getParameters().iterator();
		double returnValue = 0.0;
		
		while(iter.hasNext()){
			List<Double> inputs = createInputs(iter);
			returnValue += model.getTurtleMaster().operateOnTurtle(applyInputs(model, inputs));
		}
		return returnValue;
	}
	
	private List<Double> createInputs(Iterator<Double> iterator){
		List<Double> inputs = new ArrayList<Double>();
		for (int i = 0 ; i < getFunctionInputNumber(); i++){
			inputs.add(iterator.next());
		}
		return inputs;	
	}
	
	protected abstract Double getFunctionInputNumber();
		
	private Function<Turtle, Double> applyInputs(Model model, List<Double> inputs) {
		BiFunction<Turtle, List<Double>, Double> action = supplyAction(model);
		return turtle -> action.apply(turtle, inputs);
	}


	protected abstract BiFunction<Turtle, List<Double>,Double> supplyAction(Model model);
	
	
	
	

}
