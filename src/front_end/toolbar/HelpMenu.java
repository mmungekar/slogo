package front_end.toolbar;

import java.util.Arrays;
import java.util.List;

import front_end.customJavaFxNodes.PopUpHTML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelpMenu extends Menu{
	public static final List<String> RESOURCES_MISC_HELP_HTML = Arrays.asList("/resources/misc/basicCommands.html", "/resources/misc/extendedCommands.html", "/resources/misc/colorPalette.html");

	
	public HelpMenu(String title){
		super(title);
		MenuItem doc = new MenuItem("Documentation");
		createHelpButton(doc);
		this.getItems().add(doc);
	}
	
	private void createHelpButton(MenuItem doc)
	{
		doc.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				PopUpHTML popUpHTML = new PopUpHTML(RESOURCES_MISC_HELP_HTML);
				popUpHTML.setTitle("Help Page");
				popUpHTML.show();
			}
		});
	}
}
