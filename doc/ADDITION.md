# Estimation

## How long do you think it will take you to complete this new feature?

1 hour

## How many files will you need to add or update? Why?

Probably no more than 4 or 5. I believe I should be able to add the stamp/clear feature by adding two files for each command and modifying another three to implement the stamp and clear action.

# Review: after completing the feature:
## How long did it take you to complete this new feature?

Slightly over an hour

How many files did you need to add or update? Why?

I created two new files, one for each command, and updated the Canvas class, to give it the ability to render stamps, and the TurtleMaster class, to manage the stamps and execute commands.

Did you get it completely right on the first try?

Second try.

# Analysis: what do you feel this exercise reveals about your project's design and documentation?
##was it as good (or bad) as you remembered?

I believe our overall design is still strong. It allows for decoupling between front and back end, as well as extendability in terms of what commands another engineer could write.

However, our documentation is terrible. Our comments do little to explain much of our hard-to-read code. Thus, implementation of any other feature than commands would be significantly harder.

## What could be improved?
If we had had more time, or if we had sacrificed a couple of features, we could have refactored our code a lot more, to be much easier to read and more organized. We also could have added significantly more documentation,
including comments at least in every class.


## What would it have been like if you were not familiar with the code at all?

In this specific case, I do not believe it would have been a problem for an engineer to implement the feature. Our documentation clearly explains how to add a command to the environment, so an engineer could quickly
look at a couple of exmaple commands and be able to focus solely on implementation of methods in the back end to add the feature.

Again, it would be pretty difficult for someone else to come and try to read through the code and understand it all. This may be our project's biggest weakness; it is difficult to maintain and extend code that is hard to read
and doesn't have extensive documentation.