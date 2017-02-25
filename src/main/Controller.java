package main;

import front_end.View;
import back_end.*;
import javafx.stage.Stage;

public class Controller
{
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;


	public void start(Stage s)
	{
		Model model = new Model();
		View view = new View(s, model);
		StringInterpreter stringInterpreter = new StringInterpreter();

		view.setEnterListener((String string) ->
			stringInterpreter.interpret(string).Execute(model));
	}
}
