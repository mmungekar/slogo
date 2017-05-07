### Estimation

* The new features I aim to implement are the dual commands STAMP and CLEARSTAMPS

* Implementing these two features will likely take between 1-2 hours, given my lack of familiarity with the code as is.

* I will need to add two new classes to the commands package and update the TurtleMaster and Controller classes. This because, when the command object is called, it is passed to the controller class, which then instructs the TurtleMaster to execute the command. The actual code for placing the stamp on the canvas will be done in the Turtle class most likely. There is also a resource file which must be updated to reflect that these two new commands will require zero parameters (needed for the Expression Tree to properly parse the command). 

### Review

* It took me less time than expected (around 40-45 minutes) to complete this new feature.

* I needed to add two new command classes and update fewer files than expected. The model, in fact, had a getTurtleMaster() method, so I ended up using that to access the turtle master and writing the addStamps() and clearStamps() methods there. Nothing needed to be changed in the Turtle class; however, the canvas itself had to be updated whenever either of these methods were called.

* I didn't get it right on the first try, because I had forgotten some nuances of the code, including how updates to the canvas were made. However, once I had figured that out, the turtle images were added and removed from the screen as expected.

### Analysis

* The project's design was not bad-- the dependencies between classes exist through public methods mainly, and these public methods proved very useful (the only problem was that they were slightly difficult to find). It was actually somewhat annoying to deal with the nuances of the controller. Although the classes are kept isolated and only linked through the controller (which is a fairly good thing), looking up methods requires sifting through the entire call hierarchy to find things. Not to mention that in observer/observable bindings, it is sometimes difficult to find which classes are involved.

* A few improvements that could have been made would be in the actual organization of the code within classes. Ideally, related methods would be put close to one another, as, sometimes, a method modifying a data structure would be put in the beginning of the code, and the getter for that particular data structure would, for some reason, be at the very end. Furthermore, methods could be shortened in the classes that tended to run slightly longer.

* This whole process would have been much more confusing had I no familiarity with the code at all. For one, I would likely be confused as to which abstract class needed to be extended to create the two commands. Furthermore, I would likely have no idea how this particular ExpressionTree worked and would not have known to update the expected number of parameters for the CLEAR and CLEARSTAMPS in the resource file. If I had taken a look at some of the other commands, I likely would have been able to figure a few things out-- namely, that the actual implementation for adding and clearing stamps would have been done in TurtleMaster. However, it would have been difficult to discern how the TurtleMaster linked to the Canvas. I would have had to have done a little more searching/examining the notifyModel() method to realize that the Canvas was an observer of TurtleMaster.