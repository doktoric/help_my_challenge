set PYTHON_ENV=c:\Python27\python.exe

javac -classpath .;lib/commons-math3-3.2.jar -d target/classes -sourcepath src/main/java src/main/java/com/acme/challenge/App.java

echo 300 300 300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 300 300 300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 300 300 300 | %PYTHON_ENV% evaluator.py