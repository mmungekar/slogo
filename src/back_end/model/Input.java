package back_end.model;

public class Input {
	private String myParameter;
	// Types that follow the naming in Syntax.properties
	private String myType;
	public Input(){
		
	}
	public Input(String parameter, String type){
		myParameter = parameter;
		myType = type;
	}
	
	public String getParameter(){
		return this.myParameter;
	}
	
	public String getType(){
		return this.myType;
	}
	
	public void setType(String type){
		this.myType = type;
	}
}
