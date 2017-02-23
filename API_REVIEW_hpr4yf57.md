Part 1
======
What about your API/design is intended to be flexible?
------
* easy to add new commands without having to change pre-existing code/design with Command interface
* public methods make it easy to connect backend to frontend 

How is your API/design encapsulating your implementation decisions?
-------
* both of our groups are using a Factory to hide switch-case or if/else statements to decide which command to run on the backend

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
-----------
* String interpreter may not be able to interpret the given command; we handle this by displaying an alert on the GUI. The exception is thrown by the interpreter and caught in the Controller so we can catch a backend error and display the alert on the frontend.

Why do you think your API/design is good (also define what your measure of good is)?
------------
* our API only provides necessary methods and is easily extendible

Part 2
======
How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
-----------
* we will use the Command Pattern to implement our commands
* the interpreter will also use a Factory to determine which instance of Command to instantiate

How do you think the "advanced" Java features will help you implement your design?
----------
* regular expression
* Observable
* Factory
* Interfaces

What feature/design problem are you most excited to work on?
------
* figuring out how to interpret nested commands

What feature/design problem are you most worried about working on?
-------
* ensuring that the backend properly throws exception whenever necessary so the program never crashes

Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
-------
* fd 90: Executor.parse(SlogoView.getCommandPromptViewNode().getCurrentCommand()) -> TurtleCommandFactory.getCommand(String userInput) -> TurtleCommand.execute(String userInput) -> TurtleModel.update() -> variables.update() -> SlogoView.update(TurtleInfo)

* Click on a variable and change its value: [SLogoController]SLogoView.getVariablesViewNode().updateVariable();

* ifelse greater? sum 2 3 7 [fd 50] [bk 50] : Executor.parse(SlogoView.getCommandPromptViewNode().getCurrentCommand()) -> TurtleCommandFactory.getCommand(String userInput) -> TurtleCommand.addToStack(Command command) -> stack.pop.execute(String userInput) -> TurtleModel.update() -> variables.update() -> SlogoView.update(TurtleInfo)

* product 4 5: xecutor.parse(SlogoView.getCommandPromptViewNode().getCurrentCommand()) -> TurtleCommandFactory.getCommand(String userInput) -> TurtleCommand.execute(String userInput) -> variables.update() -> SlogoView.update(TurtleInfo)

* Clicking options: [SLogoController]SLogoView.getMenuBarViewNode().displayOptions();

