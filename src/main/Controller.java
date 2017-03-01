package main;

import front_end.View;
import back_end.*;
import javafx.stage.Stage;

public class Controller {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private View view;
	private Model model;
	private Interpreter mInterpreter;

	public void start(Stage s) {
		model = new Model();
		view = new View(s, model);

		mInterpreter = new Interpreter();

		view.setEnterListener((String rawUserInput) -> {
			System.out.println(model.toString());
			
				// This line should stay the same, all changes should happen in the backend
				try {
					view.setOutput(mInterpreter.execute(model, rawUserInput));
				} catch (UnrecognizedCommandException | NotEnoughParameterException e) {
					view.printError(e);
				}
			
			
			System.out.println(model.toString());
		});
		
	}
}
