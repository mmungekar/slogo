package main;

import front_end.View;
import back_end.*;
import back_end.exceptions.CommandException;
import javafx.stage.Stage;

public class Controller {
	private View view;
	private Model model;
	private Interpreter mInterpreter;

	public void start(Stage s) {
		model = new Model();
		view = new View(s, model);
		mInterpreter = new Interpreter();

		view.setEnterListener((String rawUserInput) -> {
			//System.out.println(model.toString());
			try {
				view.setOutput(mInterpreter.execute(model, rawUserInput));
			} catch (CommandException e) {
				view.printError(e);
			}
			//System.out.println(model.toString());
		});
	}
}
