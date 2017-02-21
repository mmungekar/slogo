package back_end;

import java.util.ResourceBundle;

public class StringInterpreter {
	private String currentLanguage;
	private ResourceBundle resources;

	public Object interpret(String commandString) {

		return null;
	}

	public void setLanguage(String language) {
		this.currentLanguage = language;
		resources = ResourceBundle.getBundle("resources/languages/" + currentLanguage);
		System.out.println("Language Changed To : " + language);

	}

}
