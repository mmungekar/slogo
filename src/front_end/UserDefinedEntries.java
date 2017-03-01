package front_end;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class UserDefinedEntries extends TabPane {
	public static final ObservableList<String> customVariables = FXCollections.observableArrayList();
	public static final ObservableList<String> customCommands = FXCollections.observableArrayList();
	private View myView;

	private Tab tabCommands;
	private Tab tabVariables;

	public UserDefinedEntries(View view) {
		tabCommands = new Tab();
		tabCommands.setContent(createCommandView());

		tabVariables = new Tab();
		tabVariables.setContent(createVariableView());

		this.getTabs().addAll(tabCommands, tabVariables);
		this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		this.myView = view;
	}

	/*
	 * I know this is repeated code. The createVariableView method will be
	 * changed once I figure out how to make it editable.
	 * 
	 * TODO: Make values in this editable, not executable
	 */
	private ListView<String> createVariableView() {
		/*
		 * TableView table = new TableView(); TableColumn variableNameCol = new
		 * TableColumn("Name"); TableColumn variableValueCol = new
		 * TableColumn("Value"); variableNameCol.setEditable(false);
		 * variableValueCol.setEditable(true);
		 */

		ListView<String> myListView = new ListView<String>();
		myListView.setItems(customVariables);
		myListView.setPrefWidth(150);
		myListView.setPrefHeight(175);
		setClickActionOnCustomListView(myListView); // this will be replaced
		return myListView;
	}

	private ListView<String> createCommandView() {
		ListView<String> myListView = new ListView<String>();
		myListView.setItems(customCommands);
		myListView.setPrefWidth(150);
		myListView.setPrefHeight(175);
		setClickActionOnCustomListView(myListView);
		return myListView;
	}

	private void setClickActionOnCustomListView(ListView<String> lW) {
		// http://stackoverflow.com/questions/23622703/deselect-an-item-on-an-javafx-listview-on-click
		lW.setCellFactory(lv -> {
			ListCell<String> cell = new ListCell<>();
			cell.textProperty().bind(cell.itemProperty());
			cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
				lW.requestFocus();
				if (!cell.isEmpty()) {
					int index = cell.getIndex();
					if (!lW.getSelectionModel().getSelectedIndices().contains(index)) {
						lW.getSelectionModel().select(index);
						myView.submitInput(cell.getItem()); // add commmand to
															// terminal and
															// execute
						lW.getSelectionModel().clearSelection(index);
					}
					event.consume();
				}
			});
			return cell;
		});
	}

	public void refreshGUITitles(ResourceBundle resource) {
		tabVariables.setText(resource.getString("CustomVariables"));
		tabCommands.setText(resource.getString("CustomCommands"));

	}
}
