package back_end.commands.commandLibrary.scene;

import java.util.function.Function;

import back_end.commands.commandLibrary.turtle.NoInputTurtleCommand;
import back_end.interfaces.CommandInterface;
import back_end.model.scene.Model;
import back_end.model.scene.Turtle;
import javafx.scene.image.ImageView;

public class Stamp extends NoInputTurtleCommand implements CommandInterface {

	@Override
	protected Function<Turtle, Double> supplyAction(Model model) {
		return turtle -> {
			ImageView image = turtle.getImageView();
			ImageView copy = new ImageView(image.getImage());
			copy.setX(image.getX());
			copy.setY(image.getY());
			model.getDrawer().placeStamp(copy);
			return (double) model.getTurtleMaster().getActiveTurtleID();
		};
	}

}
