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

	private Interpreter stringInterpreter;

	public void start(Stage s) {
		model = new Model();
		view = new View(s, model);

		stringInterpreter = new Interpreter();

		view.setEnterListener((String string) -> {
			System.out.println(model.toString());
			
			// TODO waiting on stringInterpreter command to execute and catch all errors
			/*
			try {
				stringInterpreter.parseLine(string).Execute(model);
			} catch (UnrecognizedCommandException | LibraryLookUpException | ParameterException e) {
				view.printToOutput(e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			System.out.println(model.toString());
		});
	}
}
