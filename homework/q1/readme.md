# Q1

##### Question
 
__Objective__: Implement a text based calculator application. 
Usage of Rhino, Nashorn and other similar solutions is not allowed. 
__Input​__: The input is a series of assignment expressions. 
The syntax is a subset of Java numeric expressions and operators. 

__Output__ : At the end of evaluating the series, the value of each variable is printed out. 

__Example__ ​: 

Input​: 

Following is a series of valid inputs for the program:
 
    i = 0 j = ++i x = i++ + 5 y = 5 + 3 * 10 i += y 
 

Output​:
 
    (i=37,j=1,x=6,y=35) 
 


##### Answer

the implementation info.

the implementation supports the following operations on the variable:
1. =
2. ++
3. +=

the implementation supports the flowing math operators:
1. \+
2. \-
3. \*
4. /

the implementation is agnostic to spaces and will read the input regardless of spaces.


#####Building/Compiling
the project is Maven based so you will need to run: 

    mvn clean install


#####Unit Tests

the Unit test for the implementation are located here:

    homework\q1\src\test\java\org\calculator\CalculatorTest.java

the project hase 90% covrage

#####Running 
the project can be exacted using main located here:

    homework\q1\src\main\java\org\calculator\Main.java

the input for the main execution is located here:

    \homework\q1\src\main\resources\input.txt







