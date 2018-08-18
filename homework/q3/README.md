# Q3

##### Question
Jimmy was tasked with writing a class that takes a base list of strings and a series of
transformations and applies them, returning the end result.
To better utilize all available resources, the solution was done in a multi-threaded fashion.
Explain the problems with this solution, and offer 2 alternatives. Discuss the advantages of each
approach.


##### Answer
first it looks like the threads are never started.
the problem with the solution is that the data field is modified by multiple threads.
as a result the modification to the data can be incorrect the values can be overridden and changes can be lost.
example on thread is making a modification in the mine while another thread read the value and is modifying it as well.
when they both complete the first thread writs the value but the value used by the second thread, the change was not applied and when thread two places the changed value 
back data is lost.
in order to avoid this we can use one of the flowing approaches.

1. we can synchronize the forEach method and add volatile to the list.
this will allow only one thread to make modifications to the list at a time.
each thread will do the modification and then release the method.
and the volatile will make sure that each thread has visibility to the latest reference of the list.
the problem with this solution that we will lose the advantage of the multi threading since it will allow only on thread to work on the data at a time.
the execution time will be almost the same as if we run this this one after the other.
bendite's:
    1. easy implantation.
    2. the solution blocks a section of code.
    3. no need to modify the code.

2. we can use the atomic reference inside the list.
the method forEach will be modified to not create a new list but to update values instead.
the treads will work on the same list.
we will use the get and update method that will read the value apply the change and store it atomically.
the benefit:
    1. the threads can run in parallel and work on different items in a list.
    2. bettr utilization of resources.
    3. requires a change to the code logic.
    4. the locking is done on a iteam level insted of a logic block.
 




