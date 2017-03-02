package front_end.customJavaFxNodes;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class ColorComboBox extends ComboBox<String>{

	// from
	// http://docs.oracle.com/javafx/2/ui_controls/list-view.htm
	public ColorComboBox(ObservableList<String> colors){
		super(colors);
		this.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectCell();
			}
		});

	}
	
	private static class ColorRectCell extends ListCell<String> {
		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			Rectangle rect = new Rectangle(100, 20);
			if (item != null) {
				rect.setFill(Color.web(item));
				setGraphic(rect);
			}
		}
	}
}
