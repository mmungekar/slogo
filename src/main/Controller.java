package main;

import front_end.View;
import back_end.*;
import back_end.constant.NotEnoughParameterException;
import back_end.library.UnrecognizedCommandException;
import back_end.library.VariableNotFoundException;
import back_end.model.Model;
import back_end.overhead.Interpreter;
import javafx.stage.Stage;

public class Controller {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private View view;
	private Model model;
	private Interpreter mInterpreter;
	private String language = "English";

	public void start(Stage s) {
		model = new Model();
		view = new View(s, model);

		mInterpreter = new Interpreter(language);

		String output = "";
		view.setEnterListener((String command) -> {
			System.out.println(model.toString());
			try {
				mInterpreter.execute(model, command);
			} catch (NotEnoughParameterException e) {
				
			} catch (UnrecognizedCommandException e) {
				
			} catch (VariableNotFoundException e) {
				
			}
			
			System.out.println(model.toString());
		});
		view.setOutputText(output);
	}
}
