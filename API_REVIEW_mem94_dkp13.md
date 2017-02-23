# Review 2/23
##Part 1
###1. What about your API/design is intended to be flexible?
 The view is intended to be very adaptable; the GUI parameters should easily be changed
 based on the simulation (i.e., if there are multiple turtles or an extra button needing to be added).
 The command library is also very flexible, in order to easily allow for extending it and changing the 
 structure of commands.
 
###2. How is your API/design encapsulating your implementation decisions?
 The design contains interfaces that detail the public methods needed to implement a particular class.
 The details of these classes, by that token, are encapsulated and generally hidden. Certain classes have
 dependencies on other classes that contain private methods or are generally hidden from the public.
 
###3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)? 
While parsing through the user input, exceptions thrown could include the user calling non-existent commands 
or commands with the wrong number of parameters. Errors would additionally be thrown if, after evaluation, a command does not have parameters with numerical values, or parameters which eventually return valid numerical values. Syntax errors will also be thrown when parsing.

###4. Why do you think your API/design is good (also define what your measure of good is)?
As of right now, most of the implementation details are private or encapsulated, meaning that they are extendable but not easy to completely change. "Good" indicates that the APIs are not only effective means of extending the program via public methods but also representative of the program's functionality. Our APIs currently strongly determine the workflow of the program.

##Part 2
###1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
Design Patterns, uptil this point, have not been extensively featured in the design. A suggestion posed by my partner includes adding the factory design to produce commands, something that could be extremely helpful in streamlining code (i.e. through using reflection instead of if-else statements).

###2. How do you think the "advanced" Java features will help you implement your design?
Reflection can be implemented to generate instances of commands. Lambda expressions can be stored in maps to run whenever certain strings are encountered. Regex expressions will be implemented in the parser to match patterns.

###3.What feature/design problem are you most excited to work on?
I think it will be very satisfying to execute a working Expression Tree. It is a difficult concept to implement without having done before, but it is a very clean solution to a complicated issue.

###4. What feature/design problem are you most worried about working on?
The expression tree is the aspect of the design I'm most worried about as well, just because there are many constraints to consider and a lot of potential for things to go wrong. 

###5. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

1) fd fd 50
The inner fd 50 is stored as the child of the first fd. Once the inner fd command returns a 50, the outer one is executed.

2) repeat 2 [fd 10]
"Repeat" will be a parent node with "2" and "fd 10" as its child. Fd 10 will then be notified to execute twice.

3) right add 30 20 
30 and 20 will be children of "add", which will be a child of "right". After execution, right will receive "50"

4) fd sum [fd 50] [right 20]
"50" and "20" are children of "fd" and "right" respectively. They will return values to "sum", which will return a value to the root node, "fd".

5) to circle :radius
The parser will recognize the "to" as a user-created function; nothing will be stored to the expression tree. Rather, the command library will be called, and all the user's inputed methods under "circle" will be stored.