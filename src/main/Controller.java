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

	public void start(Stage s) {
		model = new Model();
		view = new View(s, model.getState());

		stringInterpreter = new StringInterpreter();

		// view.setLanguageChangeListener((String string) ->
		// stringInterpreter.setLanguage(string));

		view.setEnterListener((String string) -> {
			System.out.println(model.getState().getY(0));
			stringInterpreter.interpret(string).Execute(model.getState());
			System.out.println(model.getState().getY(0));
		});

		initAnimation();
	}

	private void initAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step() {
		// view.update(model.getState());
	}

}
