package front_end;

import java.util.function.Consumer;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class Terminal {
	private TextArea console;

	private Consumer<String> onMessageReceivedHandler;

	public Terminal(Group root) {
		console = new TextArea();
		console.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				onMessageReceivedHandler.accept(console.getText());
				// TODO change the text in the text area
			}
		});
	}

	public void setEnterListener(final Consumer<String> action) {
		this.onMessageReceivedHandler = action;

	}

	public TextArea getConsole() {
		return console;
	}
}
