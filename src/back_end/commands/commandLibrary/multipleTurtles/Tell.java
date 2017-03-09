package back_end.commands.commandLibrary.multipleTurtles;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import back_end.commands.commandLibrary.AllParameterCommand;
import back_end.commands.constant.Constant;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Tell extends AllParameterCommand implements CommandInterface, Constant{

	@Override
	protected Function<List<Double>, Double> supplyAction(Model model) {
		return inputs -> {
			List<Integer> parametersInteger = inputs.stream()
			        .filter(elt -> elt != null)
			        .map(elt -> elt.intValue())
			        .collect(Collectors.toList());
			
			model.tell(parametersInteger);
			return parametersInteger.get(parametersInteger.size() - 1).doubleValue();
		};
	}

}
