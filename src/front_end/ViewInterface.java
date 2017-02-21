package front_end;

import java.util.function.Consumer;

import back_end.ModelState;

public interface ViewInterface {
	
	public void update(ModelState state);
	
	public void setEnterListener(Consumer<String> action);
	
	public void setLanguageChangeListener(final Consumer<String> action);
}
