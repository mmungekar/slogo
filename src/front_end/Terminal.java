package front_end;

//http://codereview.stackexchange.com/questions/52197/console-component-in-javafx
///http://docs.oracle.com/javafx/2/ui_controls/list-view.htm

import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Terminal {
	private TextArea output = new TextArea();
	private TextField input = new TextField();

	public static final int CONSOLE_WIDTH = 500;

	private VBox console;

	protected int historyPointer = 0;

	private ListView<String> historyView = new ListView<String>();
	public static final ObservableList<String> history = FXCollections.observableArrayList();

	public final static String EMPTY_STRING = "";

	private Consumer<String> onMessageReceivedHandler;

	public Terminal() {
		output.setEditable(false);
		console = new VBox();
		setupInput();
		setupHistoryView();
		console.getChildren().addAll(historyView, input);
	}

	private void setupHistoryView() {
		historyView.setItems(history);
		historyView.setPrefWidth(CONSOLE_WIDTH);
		historyView.setPrefHeight(175);		
		setMouseActionOnListView();

	}

	private void setMouseActionOnListView() {
		// http://stackoverflow.com/questions/23622703/deselect-an-item-on-an-javafx-listview-on-click
		historyView.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                historyView.requestFocus();
                if (! cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (!historyView.getSelectionModel().getSelectedIndices().contains(index)) {
                    	historyView.getSelectionModel().select(index);
                    	input.setText(cell.getItem());
    				    submitInput();
                    	historyView.getSelectionModel().clearSelection(index);
                    }
                    event.consume();
                }
            });
            return cell ;
        });
	}

	private void setupInput() {
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
		input.setPrefWidth(CONSOLE_WIDTH);
	}

	private void loadHistoryIntoTextInput() {
		System.out.println(historyPointer);
		input.setText(history.get(historyPointer));
		input.selectAll();
	}

	void submitInput() {
		String text = input.getText();
		historyView.scrollTo(history.size()-1);
		if (!text.equals(EMPTY_STRING)) {
			// output.appendText(text + System.lineSeparator());
			history.add(text);
			historyPointer = history.size();
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
	
	public void setText(String in){
		input.setText(in);
	}
	
	public String getText(){
		return input.getText();
	}
}
