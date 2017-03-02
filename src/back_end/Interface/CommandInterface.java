package back_end.Interface;

import back_end.constant.NotEnoughParameterException;
import back_end.model.Model;

public interface CommandInterface<A> {
	void setParameters(A... os) throws NotEnoughParameterException;

	double Execute(Model model);
}
