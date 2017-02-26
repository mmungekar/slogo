package front_end;

import java.util.ResourceBundle;

//http://codereview.stackexchange.com/questions/52197/console-component-in-javafx
///http://docs.oracle.com/javafx/2/ui_controls/list-view.htm

import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Terminal {
	public static final int CONSOLE_WIDTH = 500;
	public static final String LANGUAGE_DIRECTORY = "resources/languages/";
	private ResourceBundle resource;
	
	private VBox console;
	private TextArea input;
	private TextArea output;
	private ListView<String> historyView = new ListView<String>();

	protected int historyPointer = 0;
	public static final ObservableList<String> history = FXCollections.observableArrayList();
	public final static String EMPTY_STRING = "";
	private Consumer<String> onMessageReceivedHandler;

	public Terminal(String language) {
		resource = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + language);
		console = new VBox();
		console.getChildren().addAll(createInputComponents(), createHistoryAndOutputComponents());
	}

	private Node createHistoryAndOutputComponents() {
		TabPane tabPane = new TabPane();
	    
		initializeOutput();
	    Tab tabOutput = new Tab();
	    tabOutput.setText(resource.getString("Output"));
	    tabOutput.setContent(output);
	    
	    Tab tabHistory = new Tab();
	    tabHistory.setText(resource.getString("History"));
	    tabHistory.setContent(createHistoryComponents());
	    
	    tabPane.getTabs().addAll(tabOutput, tabHistory);

		tabPane.setPrefHeight(input.getPrefHeight());
		
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
	    
		return tabPane;
	}

	private void initializeOutput() {
		output = new TextArea();
		output.setEditable(false);
	}

	private HBox createInputComponents() {
		HBox inputComponents = new HBox();
		
		initializeInput();
		
		Button submit = new Button(resource.getString("Submit"));
		submit.setOnAction(event -> submitInput());
		submit.setPrefHeight(input.getPrefHeight());
		
		inputComponents.getChildren().addAll(input, submit);
		inputComponents.setSpacing(15);
		return inputComponents;
	}

	private HBox createHistoryComponents() {
		HBox historyComponents = new HBox();
		
		setupHistoryView();
		
		Button clear = new Button(resource.getString("Clear"));
		clear.setOnAction(event -> clearHistory());
		clear.setPrefHeight(historyView.getPrefHeight());
		
		historyComponents.getChildren().addAll(historyView, clear);
		historyComponents.setSpacing(15);
		return historyComponents;
	}

	private void clearHistory() {
		history.clear();
	}

	private void setupHistoryView() {
		historyView.setItems(history);
		historyView.setPrefWidth(CONSOLE_WIDTH);
		setMouseActionOnListView();

	}

	private void setMouseActionOnListView() {
		// http://stackoverflow.com/questions/23622703/deselect-an-item-on-an-javafx-listview-on-click
		historyView.setCellFactory(lv -> {
			ListCell<String> cell = new ListCell<>();
			cell.textProperty().bind(cell.itemProperty());
			cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
				historyView.requestFocus();
				if (!cell.isEmpty()) {
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
			return cell;
		});
	}

	private void initializeInput() {
		input = new TextArea();
		input.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
			switch (keyEvent.getCode()) {
			case ENTER:
				// http://stackoverflow.com/questions/19002059/get-key-combination-code
				if (keyEvent.isShiftDown()){
					submitInput();
				}
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
					input.clear();
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
		input.setPrefHeight(150);
	}

	private void loadHistoryIntoTextInput() {
		input.setText(history.get(historyPointer));
		input.selectAll();
	}

	void submitInput() {
		String text = input.getText();
		historyView.scrollTo(history.size() - 1);
		if (!text.equals(EMPTY_STRING)) {
			history.add(text.trim());
			historyPointer = history.size();
			output.clear();
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

	public void setText(String in) {
		input.setText(in);
	}

	public String getText() {
		return input.getText();
	}

	void setOutputText(String output2) {
		output.setText(output2);
		
	}

	void printToOutput(Exception e) {
		output.setText(e.getMessage());
	}
}
