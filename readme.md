
Solution for the Prezi Scale Contest
====================================

Compile:
javac -classpath .;lib/commons-math3-3.2.jar -d target/classes -sourcepath src/main/java src/main/java/com/acme/challenge/App.java

Run with evaluator:
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App | evaluator.py -d

or

java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App {log-path} {300} | evaluator.py -d
300: queue size [optional]
log-path: the logs that you want to check;)


350 350 450 :
	week1:24601
	week2:20745

