* The new features I aim to implement are the dual commands STAMP and CLEARSTAMPS

* Implementing these two features will likely take between 1-2 hours, given my lack of familiarity with the code as is.

* I will need to add two new classes to the commands package and update the TurtleMaster and Controller classes. This because, when the command object is called, it is passed to the controller class, which then instructs the TurtleMaster to execute the command. The actual code for placing the stamp on the canvas will be done in the Turtle class most likely. There is also a resource file which must be updated to reflect that these two new commands will require zero parameters (needed for the Expression Tree to properly parse the command). 

how long do you think it will take you to complete this new feature?
how many files will you need to add or update? Why?