package front_end;

import java.util.function.Consumer;

import javafx.stage.Stage;

public class View
{
	private Terminal terminal;
	private Canvass canvass;
	
	public View (Stage s)
	{
		canvass = new Canvass(s);
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
