package front_end.customJavaFxNodes;

import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class MenuOptionsList extends Menu{
	private ToggleGroup group;
	private String defaultValue;

	public MenuOptionsList(String title, ObservableList<String> options, String defaultValue, Consumer<String> action){
		super(title);
		group = new ToggleGroup();
		this.defaultValue = defaultValue;
		
		
		refreshOptions(options);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		            if (group.getSelectedToggle() != null) {
		                action.accept((String) group.getSelectedToggle().getUserData());
		            }
		        }
		 });
	}
	
	public void refreshOptions(ObservableList<String> options){
		this.getItems().clear();
		// http://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
		for(String option : options){
			 RadioMenuItem itemLang = new RadioMenuItem(option);
			 itemLang.setUserData(option);
			 itemLang.setToggleGroup(group);
			 this.getItems().add(itemLang);
			 if (option.equals(this.defaultValue)){
				 group.selectToggle(itemLang);
			 }
		}
		
	}
}
