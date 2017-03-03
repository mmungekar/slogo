package front_end.customJavaFxNodes;

import java.net.URL;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PopUpHTML extends Stage{
	public PopUpHTML(List<String> htmlFilePaths){
		Group root = new Group();
		Scene helpScene = new Scene(root);
		
		TabPane tabPane = new TabPane();
		htmlFilePaths.stream().forEach(path -> tabPane.getTabs().add(createHTMLTab(path)));
		root.getChildren().add(tabPane);
		this.setScene(helpScene);
	}

	private Tab createHTMLTab(String htmlFilePath) {
		Tab tab = new Tab();
		
		tab.setText(htmlFilePath);
		tab.setClosable(false);
		
		WebView browser = new WebView();
		URL url = getClass().getResource(htmlFilePath);
		browser.getEngine().load(url.toExternalForm());
		
		tab.setContent(browser);
		return tab;
	}
}
