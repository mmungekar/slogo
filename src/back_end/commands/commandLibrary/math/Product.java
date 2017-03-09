package back_end.commands.commandLibrary.math;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import back_end.commands.commandLibrary.AllParameterCommand;
import back_end.commands.commandLibrary.BiFunctionCommand;
import back_end.commands.commandLibrary.ProgressiveParameterCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;

public class Product extends ProgressiveParameterCommand implements CommandInterface{

	@Override
	protected BinaryOperator<Double> getProgressiveAction() {
		return (a,b) -> a * b;
	}



}
