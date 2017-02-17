SLogo API Design Exercise (Team 16)
=======================

#Team 16 Members
1. Miguel Anderson (mra21)
2. Yuansong Feng (yf57)
3. Mina Mungekar (mem94)
4. Juan Philippe (jap87)


#API Critique



#SLogo Architecture Design

### When does parsing need to take place and what does it need to start properly?

Parsing occurs after the user presses enter. Front-end takes the user input (a String) and calls a public, external API, method in the backend, to parse the input.

### What is the result of parsing and who receives it?

The user input is decoded by the back-end method, which was called by the front-end. The decoded input is then processed by the back-end which decides which command (e.g. forward, left) was called and sends the appropriate parameters (e.g. distance, speed) to it. The result of corresponding back-end method is sent to the front-end in the form of a set of coordinates for the object in question.

### When are errors detected and how are they reported?

Errors in the string user input will be handled by the back-end parser. It will throw an exception back up to the front-end which will map the exception to an action (e.g. printing an error message) that the user can see.

### What do commands know, when do they know it, and how do they get it?

Commands will know their parameters when called. The back-end calls these commands after parsing the user input and, therefore, will also give the parameters to these commands.

### How is the GUI updated after a command has completed execution?

The back-end sends information to the front-end that is the result of the command. The front-end then applies these changes. At this stage we assume a good, generalized method of sending information to the front-end would be in the form of coordinates of a new location for the object in question.

#Class-Responsibility-Collaborator Cards


#Create Your APIs

#Use Cases

For a user input, the front-end receives the string from the command window. The front-end then calls a method in the back-end to handle the input. The back-end decodes the string into commands and parameters and then sends information back to the front-end of how to display the results of the command. 

### User types: 'fd 50'

This string would be passed into the back-end. It would separate the input by spaces. It would then scan through the input words looking for commands (e.g. forward or left) in their shorthand form (e.g. fd or lt) followed by the parameters that should be sent to the corresponding command methods. The back-end will then return a vector in the form of x and y for the change in the coordinates of the turtle. The front-end will then rotate and move the turtle accordingly.

### User types: '50 fd'

This string would be passed into the back-end. It would separate the input by spaces. It would then scan through the input words looking for commands (e.g. forward or left) in their shorthand form (e.g. fd or lt) followed by the parameters that should be sent to the corresponding command methods. In this case, the first word is not a command, so the back-end will throw an exception up to the front-end. The front-end will receive the error and act accordingly. In this case, the action will be producing an error print statement. 

### User types: 'pu fd 50 pd fd 50'

### User changes color of environment background
