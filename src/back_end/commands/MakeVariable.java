package back_end.commands;

import back_end.Input;
import back_end.Interface.CommandInterface;
import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;
import back_end.model.Oxygen;

public class MakeVariable implements CommandInterface<Oxygen> {
	private String name;
	private double value;

	@Override
	public void setParameters(Oxygen... os) throws NotEnoughParameterException {
		try {
			name = (String) os[0].getContent();

		} catch (ClassCastException ex) {
			name = (String) os[0].getSubContent();
		}
		value = (Double) os[1].getContent();

	}

	@Override
	public double Execute(Model model) {
		model.mVariableLibrary.updateVariable(name, value);
		System.out.println("New variable created: " + name + " : " + value);
		return value;
	}

}
