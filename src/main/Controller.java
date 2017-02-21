package main;

import front_end.View;

import back_end.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

	private View view;
	private Model model;
	private StringInterpreter stringInterpreter;

	public void start(Stage s)
	{
		view = new View(s);
		model = new Model();
		
		stringInterpreter = new StringInterpreter();
		
		//view.setLanguageChangeListener((String string) -> stringInterpreter.setLanguage(string));

		view.setEnterListener((String string) -> model.execute(stringInterpreter.interpret(string)));

		initAnimation();
	}

	private void initAnimation()
	{
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step()
	{
		view.update(model.getChanges());
	}

}
