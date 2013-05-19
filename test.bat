set PYTHON_ENV=c:\Python27\python.exe

javac -classpath .;lib/commons-math3-3.2.jar -d target/classes -sourcepath src/main/java src/main/java/com/acme/challenge/App.java

echo 300 300 300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 300 300 300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 300 300 300 | %PYTHON_ENV% evaluator.py

echo 300 300 400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 300 300 400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 300 300 400 | %PYTHON_ENV% evaluator.py

echo 300 400 300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 300 400 300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 300 400 300 | %PYTHON_ENV% evaluator.py

echo 300 400 400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 300 400 400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 300 400 400 | %PYTHON_ENV% evaluator.py

echo 400 300 400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 400 300 400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 400 300 400 | %PYTHON_ENV% evaluator.py

echo 400 400 300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 400 400 300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 400 400 300 | %PYTHON_ENV% evaluator.py

echo 400 400 400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 400 400 400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 400 400 400 | %PYTHON_ENV% evaluator.py

echo 500 400 400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 500 400 400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 500 400 400 | %PYTHON_ENV% evaluator.py

echo 400 500 400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 400 500 400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 400 500 400 | %PYTHON_ENV% evaluator.py

echo 400 400 500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 400 400 500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 400 400 500 | %PYTHON_ENV% evaluator.py

echo 500 500 400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 500 500 400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 500 500 400 | %PYTHON_ENV% evaluator.py

echo 500 400 500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 500 400 500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 500 400 500 | %PYTHON_ENV% evaluator.py

echo 400 500 500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 400 500 500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 400 500 500 | %PYTHON_ENV% evaluator.py

echo 500 500 500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 500 500 500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 500 500 500 | %PYTHON_ENV% evaluator.py

echo 600 500 500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 600 500 500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 600 500 500 | %PYTHON_ENV% evaluator.py

echo 500 600 500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 500 600 500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 500 600 500 | %PYTHON_ENV% evaluator.py

echo 500 500 600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 500 500 600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 500 500 600 | %PYTHON_ENV% evaluator.py

echo 600 600 500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 600 600 500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 600 600 500 | %PYTHON_ENV% evaluator.py

echo 500 600 600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 500 600 600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 500 600 600 | %PYTHON_ENV% evaluator.py

echo 600 500 600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 600 500 600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 600 500 600 | %PYTHON_ENV% evaluator.py

echo 600 600 600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 600 600 600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 600 600 600 | %PYTHON_ENV% evaluator.py

echo 700 600 600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 700 600 600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 700 600 600 | %PYTHON_ENV% evaluator.py

echo 600 700 600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 600 700 600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 600 700 600 | %PYTHON_ENV% evaluator.py

echo 600 600 700
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 600 600 700 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 600 600 700 | %PYTHON_ENV% evaluator.py

echo 700 700 600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 700 700 600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 700 700 600 | %PYTHON_ENV% evaluator.py

echo 600 700 700
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 600 700 700 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 600 700 700 | %PYTHON_ENV% evaluator.py

echo 700 600 700
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 700 600 700 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 700 600 700 | %PYTHON_ENV% evaluator.py

echo 700 700 700
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 700 700 700 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 700 700 700 | %PYTHON_ENV% evaluator.py

echo 800 700 700
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 800 700 700 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 800 700 700 | %PYTHON_ENV% evaluator.py

echo 700 800 700
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 700 800 700 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 700 800 700 | %PYTHON_ENV% evaluator.py

echo 700 700 800
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 700 700 800 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 700 700 800 | %PYTHON_ENV% evaluator.py

echo 800 800 700
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 800 800 700 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 800 800 700 | %PYTHON_ENV% evaluator.py

echo 700 800 800
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 700 800 800 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 700 800 800 | %PYTHON_ENV% evaluator.py

echo 800 700 800
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 800 700 800 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 800 700 800 | %PYTHON_ENV% evaluator.py

echo 800 800 800
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 800 800 800 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 800 800 800 | %PYTHON_ENV% evaluator.py

echo 900 800 800
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 900 800 800 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 900 800 800 | %PYTHON_ENV% evaluator.py

echo 800 900 800
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 800 900 800 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 800 900 800 | %PYTHON_ENV% evaluator.py

echo 800 800 900
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 800 800 900 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 800 800 900 | %PYTHON_ENV% evaluator.py

echo 900 900 800
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 900 900 800 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 900 900 800 | %PYTHON_ENV% evaluator.py

echo 800 900 900
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 800 900 900 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 800 900 900 | %PYTHON_ENV% evaluator.py

echo 900 800 900
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 900 800 900 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 900 800 900 | %PYTHON_ENV% evaluator.py

echo 900 900 900
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 900 900 900 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 900 900 900 | %PYTHON_ENV% evaluator.py

echo 1000 900 900
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 900 900 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 900 900 | %PYTHON_ENV% evaluator.py

echo 900 1000 900
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 900 1000 900 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 900 1000 900 | %PYTHON_ENV% evaluator.py

echo 900 900 1000
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 900 900 1000 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 900 900 1000 | %PYTHON_ENV% evaluator.py

echo 1000 1000 900
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 1000 900 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 1000 900 | %PYTHON_ENV% evaluator.py

echo 900 1000 1000
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 900 1000 1000 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 900 1000 1000 | %PYTHON_ENV% evaluator.py

echo 1000 900 1000
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 900 1000 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 900 1000 | %PYTHON_ENV% evaluator.py

echo 1000 1000 1000
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 1000 1000 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 1000 1000 | %PYTHON_ENV% evaluator.py