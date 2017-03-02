package back_end.commands.presetLibrary;

import back_end.exceptions.NotEnoughParameterException;
import back_end.interfaces.CommandInterface;
import back_end.model.Model;
import back_end.model.Oxygen;

public class MakeVariable implements CommandInterface<Oxygen>{
    private String name;
    private double value;
	@Override
	public void setParameters(Oxygen... os) throws NotEnoughParameterException {
		// TODO Auto-generated method stub
		name = (String)os[0].getContent();
		value = (Double)os[1].getContent();
	}

	@Override
	public double Execute(Model model) {
		model.mVariableLibrary.insertVariable(name, value);
		System.out.println(name + " : " + value );
		return value;
	}
	

}
