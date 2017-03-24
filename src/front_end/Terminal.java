package front_end;

//http://codereview.stackexchange.com/questions/52197/console-component-in-javafx
///http://docs.oracle.com/javafx/2/ui_controls/list-view.htm
import java.util.ResourceBundle;
import java.util.function.Consumer;
import front_end.customJavaFxNodes.ActionButton;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * By Miguel Anderson, Juan Philippe
 *
 */
public class Terminal extends VBox {
	public static final int CONSOLE_WIDTH = 500;
	private TextArea inputConsole;
	private TextArea outputConsole;
	private ListView<String> historyView = new ListView<String>();
	private int historyPointer = 0;
	public static final ObservableList<String> history = FXCollections.observableArrayList();
	public final static String EMPTY_STRING = "";
	private Consumer<String> onMessageReceivedHandler;
	// all objects that have titles
	private Tab outputHolder;
	private Tab historyHolder;
	private Button submit;
	private Button clear;

	/**
	 * Creates terminal
	 * Holds input, history, and output
	 */
	public Terminal() {
		initializeInput();
		initializeOutput();
		setupHistoryView();
		clear = new ActionButton(event -> clearHistory());
		submit = new ActionButton(event -> submitInput());
		this.getChildren().addAll(createConsoleComponents(inputConsole, submit), createHistoryAndOutputComponents());
	}

	private Node createHistoryAndOutputComponents() {
		TabPane tabPane = new TabPane();

		outputHolder = new Tab();
		outputHolder.setContent(outputConsole);

		historyHolder = new Tab();
		historyHolder.setContent(createConsoleComponents(historyView, clear));
		tabPane.getTabs().addAll(outputHolder, historyHolder);
		tabPane.setPrefHeight(inputConsole.getPrefHeight());
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		return tabPane;
	}

	private void initializeOutput() {
		outputConsole = new TextArea();
		outputConsole.setEditable(false);
	}

	private HBox createConsoleComponents(Region mainConsole, Button button) {
		HBox consoleComponents = new HBox();
		button.setPrefHeight(mainConsole.getPrefHeight());
		consoleComponents.getChildren().addAll(mainConsole, button);
		consoleComponents.setSpacing(15);
		return consoleComponents;
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
						submitInput(cell.getItem());
						historyView.getSelectionModel().clearSelection(index);
					}
					event.consume();
				}
			});
			return cell;
		});
	}

	private void initializeInput() {
		inputConsole = new TextArea();
		inputConsole.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
			switch (keyEvent.getCode()) {
			case ENTER:
				// http://stackoverflow.com/questions/19002059/get-key-combination-code
				if (keyEvent.isShiftDown()) {
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
				if (historyPointer >= history.size() - 1) {
					inputConsole.clear();
					break;
				}
				historyPointer++;
				loadHistoryIntoTextInput();
				break;
			default:
				break;
			}
		});
		inputConsole.setPrefWidth(CONSOLE_WIDTH);
		inputConsole.setPrefHeight(150);
	}

	private void loadHistoryIntoTextInput() {
		inputConsole.setText(history.get(historyPointer));
		inputConsole.selectAll();
	}

	/**
	 * shortcut to submit a string to the Model
	 * 
	 * @param in
	 */
	public void submitInput(String in) {
		inputConsole.setText(in);
		submitInput();
	}

	public void submitInput() {
		String text = inputConsole.getText();
		historyView.scrollTo(history.size() - 1);
		if (!text.equals(EMPTY_STRING)) {
			history.add(text.trim());
			historyPointer = history.size();
			outputConsole.clear();
			if (onMessageReceivedHandler != null) {
				onMessageReceivedHandler.accept(text);
			}
			inputConsole.clear();
		}
	}

	public void setEnterListener(final Consumer<String> action) {
		this.onMessageReceivedHandler = action;
	}

	public void setText(String in) {
		inputConsole.setText(in);
	}

	public String getText() {
		return inputConsole.getText();
	}

	public void setOutputText(String output2) {
		outputConsole.setText(output2);
	}

	public void printToOutput(Exception e) {
		outputConsole.setText(e.getMessage());
	}

	/**
	 * Updates the titles of the buttons, tabs and such to the language specified by the resource file.
	 * 
	 * @param resource - where the gui titles are stored
	 */
	public void refreshGUITitles(ResourceBundle resource) {
		this.clearHistory();
		historyHolder.setText(resource.getString("History"));
		outputHolder.setText(resource.getString("Output"));
		clear.setText(resource.getString("Clear"));
		submit.setText(resource.getString("Submit"));
	}
}