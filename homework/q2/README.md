# Q2

##### Question
The following class has several memory and runtime inefficiencies and bugs. Locate and fix as many as you can. 


##### Answer
 issues:
 1. the equals compares only one item
 2. hash code was not implemented
 3. fixing constructor to eliminate null values
 4. tostring prints just part of the object
 5. the tostring use string literals
 6. removestring here there is a bug when you delete an item you change the index of the item after it
     as a result you will skip some items.
     to fix this i add the use of removeif wcich will iterate over the collection and call remove.
 7. if the list is a linked list this will be done in o(n)
 8. containsNumber to speed up the method we can change the list to map to make it easier to fid matches.
 



