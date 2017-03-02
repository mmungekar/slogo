package back_end.interfaces;

import back_end.exceptions.NotEnoughParameterException;
import back_end.model.Model;

public interface CommandInterface<A> {
	void setParameters(A... os) throws NotEnoughParameterException;

	double Execute(Model model);
}
