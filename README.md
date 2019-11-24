Project Name : Construction Site Clearance Simulator and cost estimator
 


#### HOW TO RUN THE SIMULATION##########
 
##To run from Eclipse/IntelliJ

Run the 'SiteCleanSimulation' with File name as input (input/SiteMap1.txt)

##To build the application:
 ./gradlew clean build
 
 
##To run the application:

./gradlew run --args <file name>
eg.>>  
  ./gradlew run --args input/SiteMap1.txt
 
   
java -jar build/libs/site-clearing-simulation.jar input/SiteMap1.txt

######DEVELOPEEMNT CYCLES######
 
a)Created a Java project in IntelliJ IDEA with src and test directories  created.

b)included Junit.jupiter in the classpath

c)create basic functionality test cases,

d)and the code accordingly to pass the test cases, re test, and fix /change source code..

e)extend the test cases to include more extensive testing

f) rework on the code to pass these test cases,.
