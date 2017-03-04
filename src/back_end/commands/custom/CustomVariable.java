package back_end.commands.custom;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Pair;

public class CustomVariable extends Pair<String,Double>{
	private final SimpleStringProperty name;
	private final SimpleDoubleProperty value;

	public CustomVariable(String key, Double value) {
		super(key, value);
		this.name = new SimpleStringProperty(key);
		this.value = new SimpleDoubleProperty(value);
	}

	public Double getValue() {
		return value.get();
	}
	
	public String getName(){
		return name.get();
	}
	
	public void setName(String newName){
		this.name.set(newName);
	}
	
	public void setValue(Double newValue){
		this.value.set(newValue);
	}

}
