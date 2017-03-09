package back_end.commands.commandLibrary.movement;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

import back_end.commands.commandLibrary.FunctionCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;

public abstract class RotationCommand extends FunctionCommand implements CommandInterface{
	
	protected Function<List<Double>, Double> supplyRotateAction(Model model, BiFunction<Turtle, Double, Double> rotateAction){
		return inputs -> model.operateOnTurtle(turtle -> turtle.setAngle(rotateAction.apply(turtle, inputs.get(0))));
	}
	
	protected BiFunction<Turtle, Double, Double> angleChange(int direction) {
		return (turtle, dAngle) -> turtle.getAngle() + direction * dAngle;
	}

}
