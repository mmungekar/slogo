package front_end.customJavaFxNodes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ActionButton extends Button{
	
	public ActionButton(EventHandler<ActionEvent> eventHandler){
		this.setOnAction(eventHandler);
	}
	
	public ActionButton(String title, EventHandler<ActionEvent> eventHandler){
		this(eventHandler);
		this.setText(title);
	}
}
