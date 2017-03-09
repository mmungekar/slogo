package back_end.commands.commandLibrary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public abstract class DistributiveParameterCommand extends SimpleParameterCommand implements CommandInterface{
	
	@Override
	public double Execute(Model model) throws CommandException, VariableNotFoundException, CommandException {
		Function<List<Double>,Double> action = this.supplyAction(model);
		return scanThroughInputs(action, getInputNumber());
	}	
	
	private double scanThroughInputs(Function<List<Double>, Double> action, int N){
		Double returnVal = 0.0;
		
		Iterator<Double> iter = getParameterValues().iterator();
		
		ArrayList<Double> inputs = new ArrayList<Double>();
		while(iter.hasNext()){
			for(int i = 0; i < N ; i++){
				inputs.add(i, iter.next());
			}
			returnVal += action.apply(inputs);
		}
		return returnVal;
	}
	
	protected abstract int getInputNumber();
	
}
