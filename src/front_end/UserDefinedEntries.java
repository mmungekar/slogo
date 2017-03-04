package front_end;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import back_end.commands.custom.CustomVariable;
import back_end.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DoubleStringConverter;

public class UserDefinedEntries extends TabPane implements Observer {
	private ObservableList<CustomVariable> customVariables = FXCollections.observableArrayList();
	private ObservableList<String> customCommands = FXCollections.observableArrayList();
	private View myView;

	private Tab tabCommands;
	private Tab tabVariables;
	private TableView<CustomVariable> tableVariables;

	private Model myModel;

	public UserDefinedEntries(Model model, View view) {
		this.myModel = model;
		model.addObserver(this);

		tabCommands = new Tab();
		tabCommands.setContent(createCommandView());

		tabVariables = new Tab();
		tabVariables.setContent(createVariableView());

		this.getTabs().addAll(tabCommands, tabVariables);
		this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		this.myView = view;
	}

	private Node createVariableView() {
		// http://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJAGDAHE
		// http://stackoverflow.com/questions/20020037/editing-a-number-cell-in-a-tableview
		tableVariables = new TableView<CustomVariable>();
		tableVariables.setEditable(true);
		TableColumn<CustomVariable, String> variableNameCol = new TableColumn<CustomVariable, String>("Name");
		variableNameCol.setCellValueFactory(new PropertyValueFactory<CustomVariable, String>("Name"));

		TableColumn<CustomVariable, Double> variableValueCol = new TableColumn<CustomVariable, Double>("Value");
		variableValueCol.setCellValueFactory(new PropertyValueFactory<CustomVariable, Double>("Value"));
		variableValueCol
				.setCellFactory(TextFieldTableCell.<CustomVariable, Double>forTableColumn(new DoubleStringConverter()));
		variableValueCol.setOnEditCommit(new EventHandler<CellEditEvent<CustomVariable, Double>>() {
			@Override
			public void handle(CellEditEvent<CustomVariable, Double> t) {
				((CustomVariable) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setValue(t.getNewValue());
			}
		});

		tableVariables.getColumns().addAll(variableNameCol, variableValueCol);
		tableVariables.setItems(customVariables);

		return tableVariables;
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

	@Override
	public void update(Observable o, Object arg) {
		if (myModel == o) {
			customVariables = FXCollections.observableArrayList(myModel.getUserDefinedVariables());
			customCommands = FXCollections.observableArrayList(myModel.getUserDefinedCommands());
			updateVisualLibraries();
		}

	}

	private void updateVisualLibraries() {
		
		((ListView<String>) tabCommands.getContent()).setItems(customCommands);
		tableVariables.setItems(customVariables);
	}
}
