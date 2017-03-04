package back_end.commands.presetLibrary;

import java.util.List;
import java.util.stream.Collectors;

import back_end.commands.abstracts.SimpleParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class Tell extends SimpleParameterCommand implements CommandInterface<Oxygen<Double>>, Constant{

	@Override
	public double Execute(Model model) {
		List<Double> parametersDouble = this.getParameterValue();
		
		List<Integer> parametersInteger = parametersDouble.stream()
		        .filter(elt -> elt != null)
		        .map(elt -> elt.intValue())
		        .collect(Collectors.toList());
		
		model.tell(parametersInteger);
		return parametersInteger.get(parametersInteger.size() - 1);
	}
}
