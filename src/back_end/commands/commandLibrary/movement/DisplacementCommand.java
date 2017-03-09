package back_end.commands.commandLibrary.movement;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

import back_end.commands.commandLibrary.FunctionCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
import javafx.geometry.Point2D;

public abstract class DisplacementCommand extends FunctionCommand implements CommandInterface{
	
	protected Function<List<Double>, Double> displaceTurtle(Model model, int direction){
		BiFunction<Turtle, Double, Point2D> displacementFunction = (turtle, mag) -> moveForward(turtle, direction * mag);
		return inputs -> model.operateOnTurtle(turtle -> turtle.setPosition(displacementFunction.apply(turtle, inputs.get(0))));
	}
	
	protected Point2D moveForward(Turtle turtle, double mag){
		double angle = turtle.getAngle();
		double dx = Math.cos(Math.toRadians(angle)) * mag;
		double dy = Math.sin(Math.toRadians(angle)) * mag;
		return turtle.getCenterPosition().add(dx, dy);	
	}
	
	@Override
	protected DoubleBinaryOperator getHowToHandlePreviousValue() {
		return (prevValue, result) -> prevValue + result;
	}
	
}
