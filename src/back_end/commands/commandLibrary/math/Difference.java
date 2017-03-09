package back_end.commands.commandLibrary.math;

import back_end.commands.commandLibrary.SimpleParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Difference extends SimpleParameterCommand implements CommandInterface{
	@Override
	public double Execute(Model state) {
		double difference = getParameterValue().get(0);
		for(int i = 1; i<getParameterValue().size();i++){
			difference -= getParameterValue().get(i);
		}
		return difference;
	}
}
