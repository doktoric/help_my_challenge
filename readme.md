Compile:
javac -d target/classes -sourcepath src/main/java src/main/java/com/acme/challenge/App.java

Run with evaluator:
java -cp target/classes com.acme.challenge.App | evaluator.py