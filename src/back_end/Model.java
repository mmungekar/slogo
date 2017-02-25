package back_end;

public class Model
{
	private ModelState state;
	public Model()
	{
		state = new ModelState();
	}
	
	public ModelState getState()
	{
		return state;
	}
	
	public String printState(){
		return state.toString();
		
	}
	

}
