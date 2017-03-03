package back_end.commands.abstracts;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.model.Oxygen;

public abstract class SimpleParameterCommand implements CommandInterface<Oxygen<Double>>{
	private Double[] parameters;
	
	public void setParameters(Oxygen<Double>... ds) throws NotEnoughParameterException {
		parameters = new Double[ds.length];
		for(int i = 0; i < ds.length ; i++){
			parameters[i] = ds[i].getContent();
		}
	}
	
	protected List<Double> getParameterValue() {
		return Collections.unmodifiableList(Arrays.asList(parameters));
	}
}
