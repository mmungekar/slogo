package back_end.commands.abstracts;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.model.Oxygen;

public abstract class PresetCommand implements CommandInterface<Oxygen<Double>>{
	private Double[] parameters;
	
	public void setParameters(Oxygen<Double>... ds) throws NotEnoughParameterException {
		parameters = new Double[ds.length];
		for(int i = 0; i < ds.length ; i++){
			parameters[i] = ds[i].getContent();
		}
	}
	
	protected Double[] getParameterValue() {
		return parameters;
	}
}
