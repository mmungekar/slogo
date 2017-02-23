Slogo API Review
=====

## Part 1
### What about your API/design is intended to be flexible?
I am working on the library of commands. I use a Command interface, with a setParameters method and an execute method that receives a modelstate to manipulate. this shows flexibility because I can extend any features by simply adding instance fields to the model and then adding commands to directly modify those features.

### How is your API/design encapsulating your implementation decisions?
The Command interface allows me to encapsulate my implementation decisions, because the neither the View nor the Controller need to know what or how the Command functions, it simply needs to execute the command and give it the state of the model to modify. The controller and view also don't need to worry about implementation because the StringInterpreter class will give the command its parameters, returning a complete and ready-to-execute command to the controller.

### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
The exceptions I will be handling will be relevant to the bounds of the model. For example, a movement command must be limited to the physical bounds of the the Canvas; this will be dealt with within the movement command by using the modelstate getters. Pretty much every command will handle errors. 

### Why do you think your API/design is good (also define what your measure of good is)?
I think this design is good, meaning but not limited to the fact that it is flexible and encapsulated. I also think it is good because even as we change our back end to use an expression tree, we can still use these commands the exact same way.

##Part 2
### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
The Observable/Observer interface will be very useful in keeping our View updated with the model while keeping data flow clean.

### How do you think the "advanced" Java features will help you implement your design?
Reflection will be very useful to help the back end decide which command to build, simply based on the name of the command in the properties file.

### What feature/design problem are you most excited to work on?
I am excited to implement the 'to' command, because that will require some out of the box thinking.

### What feature/design problem are you most worried about working on?
I am worried about getting our stringinterpreter to work to the full extent it needs to (loops, return values from other functions, etc). It appears to be really complicated

### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

- user calls forward command

- StringInterpreter returns a new command to Controller

- Controller executes the command, passing the command the Model's state to be modified

-Command modifies state of the model

-Observer in Canvas is notified of change, updates and renders