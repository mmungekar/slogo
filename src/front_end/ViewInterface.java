package front_end;

import java.util.function.Consumer;

public interface ViewInterface {
	
	public void setEnterListener(Consumer<String> action);
	
	public void setOutput(String message);
	
	public void submitInput(String input);
}
