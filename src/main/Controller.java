package main;

import front_end.View;
import back_end.*;
import commands.ParameterException;
import javafx.stage.Stage;

public class Controller {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private View view;
	private Model model;
	private StringInterpreter stringInterpreter;

	public void start(Stage s) {
		model = new Model();
		view = new View(s, model);

		stringInterpreter = new StringInterpreter();

		view.setEnterListener((String string) -> {
			System.out.println(model.toString());
			try {
				stringInterpreter.interpret(string).Execute(model);
			} catch (UnrecognizedCommandException | LibraryLookUpException | ParameterException e) {
				view.printToOutput(e);
			}
			System.out.println(model.toString());
		});
	}
}
