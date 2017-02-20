package front_end;

import java.util.function.Consumer;

import javafx.stage.Stage;

public class View
{
	private Terminal terminal;
	private Canvas canvas;
	
	public View (Stage s)
	{
		canvas = new Canvas(s);
		terminal = new Terminal();
		
	}

	public void update(Object changes)
	{
		if (changes == null) return;
		
		
	}

	public void setEnterListener(Consumer<String> action) 
	{
		terminal.setEnterListener(action);
		
	}
}
