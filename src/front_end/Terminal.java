package front_end;

//http://codereview.stackexchange.com/questions/52197/console-component-in-javafx

import java.util.ArrayList;
import java.util.List;

import java.util.function.Consumer;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.VBox;

public class Terminal {
	private TextArea output = new TextArea();
	private TextField input = new TextField();

	private VBox console;

	protected final List<String> history = new ArrayList<>();
	protected int historyPointer = 0;
	
	public final static String EMPTY_STRING = "";

	private Consumer<String> onMessageReceivedHandler;

	public Terminal() {
		output.setEditable(false);
		console = new VBox();
		input.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
			switch (keyEvent.getCode()) {
			case ENTER:
				submitInput();
				break;
			case UP:
				if (historyPointer == 0) {
					break;
				}
				historyPointer--;
				loadHistoryIntoTextInput();

				break;
			case DOWN:
				if (historyPointer == history.size() - 1) {
					break;
				}
				historyPointer++;
				loadHistoryIntoTextInput();

				break;
			default:
				break;
			}
		});

		console.getChildren().addAll(input, output);
	}

	private void loadHistoryIntoTextInput() {
		input.setText(history.get(historyPointer));
		input.selectAll();
	}

	void submitInput() {
		String text = input.getText();
		if(!text.equals(EMPTY_STRING)){
			output.appendText(text + System.lineSeparator());
			history.add(text);
			historyPointer++;
			if (onMessageReceivedHandler != null) {
				onMessageReceivedHandler.accept(text);
			}
			input.clear();
		}
	}

	public void setEnterListener(final Consumer<String> action) {
		this.onMessageReceivedHandler = action;

	}

	public VBox getConsole() {
		return console;
	}
}
