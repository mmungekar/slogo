package front_end.customJavaFxNodes;

import java.net.URL;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PopUpHTML extends Stage{
	public PopUpHTML(String htmlFilePath){
		Group root = new Group();
		WebView browser = new WebView();
		Scene helpScene = new Scene(root);
		root.getChildren().add(browser);
		this.setScene(helpScene);
		URL url = getClass().getResource(htmlFilePath);
		browser.getEngine().load(url.toExternalForm());
	}
}
