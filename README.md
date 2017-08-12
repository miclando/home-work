# home-work


#### Input file location:
    src/main/resources

#### Exacution:
execute:
 
    mvn clean install
    
to create the executable jar in the target dir
to run it exacute:

    java -jar homework-1.0.jar <input file path> <output file dir>

Example:

    java -jar homework-1.0.jar c:\resources\input_3 c:\resources\output

#### run time complexity
o(n!)

#### Scalabelty:

the solution as it is is not scalable becouse it is single threaded.    
to scale the solution we can seperate the work betwan a number of threads.
which will allow a fester solution.

#### improvments:
in case of more time.   
we can seperate the work between a number of machines so that the processing can be done across a number of machines and the result of each can be agregated into a singel sulution.


