package front_end;

import java.util.function.Consumer;

public interface ViewInterface {
	
	public void update(Object changes);
	
	public void setEnterListener(Consumer<String> action);
	
	public void setLanguageChangeListener(final Consumer<String> action);
}
