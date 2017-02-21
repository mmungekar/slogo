# Plan
## Introduction
We are attempting to create a library and environment for a simplified version of the Logo (SLogo) programming language. We plan to use the Model-View-Controller design structure. We want the View/GUI to be extremely simple. It will have a canvas with objects that can move, for example, a turtle. Below the canvas will be a terminal where the user can type commands to alter the state of the application, whether that be the state of the user interface or the turtle's position. The string input will be sent to a text interpreter (i.e. Controller) that creates a chain of commands that alters the user interface. These commands are executed by the model and the results are sent to the view to be updated. The setup will allow for each section to be separate and extendable. The closed parts of our project will be that strings are sent from the view to the controller and then commands are sent from the controller to the model followed by actions being applied from the model to the view. The structure is open to extension for it allows any number of commands for any number of turtles to be implemented.

## Design Overview
We plan to implement a model-view-controller design structure to separate frontend and backend tasks. A front-end animation class will run, constantly checking for updates in the view. If the user enters an input into the view (or clicks a button), it will be passed to an interpreter class that is part of the controller. The interpreter (backend) will be responsible for parsing the input and interpreting it as Java commands. Its output will then be sent to the model class, which will evaluate and communicate the result of these instructions with the view. The interpreter’s operations, therefore, will run unimpeded regardless of whether it is passed a text input or if a button is clicked. The model, for its part, will hold the instances of all objects contained within the view, including the turtle object. The view will essentially print all updates that happen in the model.

The APIs we plan on employing are:

Internal:

How the text interpreter converts string into command object
How the object changes its state inside the model, including possibility of more than one turtle

External:

Controller receives the user input in the format of String or Button clicks
Model listens for controller’s Command input
View actively updates itself based on the changes received from Model 


## User Interface
To begin with, the UI will consist of a text box, the use of keys and the ENTER button, and a turtle. The user will type commands into the text box and hit the enter button to initiate turtle movement or change aspects of the application. Erroneous situations reported to the user will include faulty or non-existent input data, which will be detected by the controller, and an error string output will be passed to the terminal in the View. Possible collisions between the turtle and other objects will be detected by the model; invalid motions (i.e. something that takes the turtle off the screen) will also be detected and reported to the user.  

## API Details 
### Internal API
#### Controller -> TextInterpreter -> interpret(String s)
This api is responsible for translating the text given by the user into executable commands. This should be the majority of our work at current stage, which is the language processing. We decide to open this part of code to the public because future programmer should be allowed to modify how they interpret a text input. For example he could potentially implement it backwards, or bottom up. 
This method could throw an LogoException; specifically, the InvalidInputLogoException will pop up. This error will alert the model that an invalid input was inputted and that the View should print out the invalid input with a string quickly explaining the error.

#### Model -> Turtle -> update(Command)
This API is responsible for receiving a list of commands and update the turtle state accordingly. It involves how to deal with specific state change inside the turtle object, for example how to mark the state as moving forward for 50 units or changing speed. 
Assuming the commands were interpretered correctly, there should be no errors in this code. Because the commands can only come from the Controller and there is an exception there for invalid inputs, no exception is needed here.

### External API
#### Controller -> TextInterpreter -> receiveInput()
The interpreter in our controller department is able to receive input from view, specifically the text field. This communication is the major method of delivering messages between the view class and the controller. We attempt to make this communication simple and easy, such that view is only loosely bound to the controller class. Controller will not care about the existence of the view since it is just trying to interpret whatever is fed to it, and the view doesn’t care about who will receive the message it outputs. 


#### Model -> Turtle -> receiveCommand()
This is our another external api which is responsible for communication between the controller and the model. The turtle will receive our command as an interface and changes its own state. With the commands being the executable messages already, this api should not have lots of work to do other than passing the commands to the turtle and watches the turtle to execute them on itself. 

### Justification
Because we are using the MVC (Model-View-Controller) design structure, we require a Model, View and Controller class. Each class is a necessary base for the three distinct parts of the project. 

## API Example Code
##### The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.

ActionListener for ‘ENTER’ key is activated in View, reads in the line and calls a Consumer which was passed in from the Controller upon initialization, feeding the consumer the String read in from the line. The Controller (which contains an instance of the View, the Model and the Interpreter) then calls Model.executeCommand( StringInterpreter.interpret(“fd 50”)), which is the string received from the View’s ActionListener. The Controller then calls View.change(Model.getChanges). getChanges passes an object that defines what changes the front end needs to render. The View then has its own implementation of how to render the changes.

#### Custom Use Cases
##### Juan
Initializing and using the command library

Initialize CommandLibrary class in StringInterpreter. 
CommandLibrary uses english properties file by default to map strings (the first word in any line) to a given Command object. 
StringInterpreter.interpret calls Command command = CommandLibrary.getCommand(Scanner.next());. 
Then it can call command.setParameters(Scanner.nextLine());. 
Which lets the command figure out how to set its own parameters depending on what type of command it is, using the rest of the line after the first word. 

Adding parameters to a Command

In StringInterpreter, use command.setParameters to feed the new Command object the rest of the line. Use PatternTracking to make sure that the string fits the correct format for a specific set of parameters associated with the Command. If no errors, then Command object initializes a Parameter type instance field with the given values. The Command is then returned to the Controller to be passed to the Model with the Parameters data.

##### Yuansong
Design the exceptions of all kinds and figure out the most reasonable place to throw/catch them. 

User types non-standard function names in the input field without defining it beforehand.

An LogoException will be thrown. Specifically, the InvalidInputLogoException will pop up and we will catch it right away in the controller class. We will then call a listener in the Canvass which is in the View part and tells it that something goes wrong. A red text alert will pop up below the user’s text field, indicating that the input is invalid and the program doesn’t know how to execute it. 

Together with Juan, discuss and create the command library. The command library will include both the pre-set functions of the game including fd, tr, etc, and the user-defined functions like toSquare(). We will figure out the best way of storing commands or user-defined functions such that it is convenient to create and destroy, as well as handy to utilize. 

 Use Case: User types:

to myAction

repeat 10 [forward 2 right 3]

END

In this case, the user has defined a set of commands as his own function called myAction. The functionBuilder class will now collect the list of commands inside the function brackets and store them in a map as the value, and the key for this particular value will be the defined function name. Next time when the user types in the same name, instead of calling back the Canvass for a potential invalid input exception, we first check our user-defined library whether there is a key name for this function. 

##### Miguel

The user types “make :distance 60”

The ActionListener attached to the ENTER key is fired and sends the entire line of text to the Controller. The Controller passes the string to the StringInterpreter, which scans the first word “make” and alerts the interpreter that it will create a new makeVariableCommand which will anticipate the next two inputs to be the name of the variable and the value. The Model, when it reaches this command in it input, will first check to see if the variable has already been defined. If it has, it will replace the value. If it has not, it will create a new variable and store it in the variable library.

       2. The user changes the language

There will be a dropdown menu in the GUI that will allow for language selection. When this is selected the choice is sent up to the controller and then fed into the StringInterpreter via StringInterpreter.setLanguage(). Now the StringInterpreter will use a different properties file for its command library.
			
##### Mina
The user types “repeat 10 [forward 2 right 3]

The ActionListener attached to the ENTER key is fired and sends the entire line of text to the Controller. The Controller passes the string to the StringInterpreter, which scans the first word “repeat” and alerts the interpreter that it needs to send multiple instances of the same command. The number 10 which follows the “repeat” instructs the Controller to send 10 such command object to the Model, which passes them one by one to the View. The “forward 2 right 3” are separately parsed to generate their own corresponding instructions via StringInterpreter.interpret(). For every “forward 2 right 3”, Model.executeCommand changes the turtle’s state once and passes it to View via the change() method.

2. The user types:
to square :size

		repeat 4 [ fd :size rt 90 

		END”

The ActionListener attached to the ENTER key is triggered, and multiple lines of text are sent to the Controller. The Controller passes the text to the StringInterpreter, which scans the “to” and initializes a CommandBuilder object. It continues parsing through the text, adding the name “square” to the command library (through a .addName() method) and recognizing that the :size delimiter is an input. The remainder of the function is processed by StringInterpreter.interpret() and converted to appropriate syntax. Instead of being passed to the Model, it is stored to the CommandBuilder object, which then creates a new command with a .newCommand() method. This new square command is placed in the command library and mapped to the word “square”.

## Design Considerations 
We extensively debated where commands would be accessed from and which class would be assigned the task of adding commands to a library or accessing this library. The process we currently intend to execute includes reading in a string input, splitting it up by spaces or special characters, and mapping each resulting array to commands within a command library. This command library will reside within the realm of the controller, so that it can be accessed by the text interpreter. If the user is creating a new user-defined function (prefaced by a “TO”), the command class will add the new command to the pre-existing library.

Another point of consideration included the timeline used in the simulation. Since the turtle’s movements will be mapped by a timeline object, we debated the position of the update function and its functionality. We eventually decided that the model class will constantly send the view states, whether they actually reflect a change in the UI or not. The only times an actual change will be triggered is if the user enters a command, which will then initiate the full cycle of processing and displaying the results of the command onscreen.

A consideration we have yet to concretely agree upon is error-handling, which can be done by multiple classes (i.e., a bad input error might be processed by the text interpreter, while a collision/invalid motion error would be handled by the model class). The question remains as to whether an error should be thrown before or after the command the user enters are sent to the StringInterpreter. On one hand, harder-to-detect syntax errors are already caught by this class, so it organizationally makes sense for it to handle other text input errors too. On the other hand, the view itself could be alerted whenever an obvious mistake is made (i.e. no input) and would simply save the Controller work by throwing an error and not passing any string at all.


## Team Responsibilities
Feng will focus on building the model and handling the state of the turtle(s) in the back end. He will also work with Juan on building a library of commands and variables. Juan will also focus on overall design and integration of different components. Miguel will focus on the View, which receives commands to change either component state (turtle) or environment state (GUI) from the model. Mina will work on the text interpreter that receives a user-input string to translate into commands. 
