package main;

import front_end.View;
import back_end.model.Model;
import back_end.overhead.Interpreter;
import back_end.exceptions.CommandException;
import back_end.exceptions.VariableNotFoundException;
import javafx.scene.control.Tab;

public class Controller {
	public static final String RETURN_PREFIX = "Returns: %s";
	public static final String OUTPUT_STRING_FORMAT = "User Input: %1$s\n\n%2$s";
	private View view;
	private Model model;
	private Interpreter mInterpreter;

	public void start(Tab tab) {
		model = new Model();
		view = new View(tab, model);
		mInterpreter = new Interpreter();

		view.setEnterListener((String rawUserInput) -> {
			//System.out.println(model.toString());
			String message;
			try {
				message = String.format(RETURN_PREFIX, mInterpreter.execute(model, rawUserInput));
			} catch (VariableNotFoundException |CommandException e) {
				message = e.getMessage();
			}
			view.setOutput(String.format(OUTPUT_STRING_FORMAT, rawUserInput, message));
			//System.out.println(model.toString());
		});
	}

	public void setNewTabButton(Runnable r)
	{
		view.setNewTabButton(r);
	}
}
