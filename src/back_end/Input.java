package back_end;

public class Input {
	private String myParameter;
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
}
