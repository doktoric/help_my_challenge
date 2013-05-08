Compile:
javac -classpath .;lib/commons-math3-3.2.jar -d target/classes -sourcepath src/main/java src/main/java/com/acme/challenge/App.java

Run with evaluator:
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App | evaluator.py -d