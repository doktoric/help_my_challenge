set PYTHON_ENV=c:\Python27\python.exe

javac -classpath .;lib/commons-math3-3.2.jar -d target/classes -sourcepath src/main/java src/main/java/com/acme/challenge/App.java

echo 1000 1000 1000
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 1000 1000 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 1000 1000 | %PYTHON_ENV% evaluator.py

echo 1000 1000 1100
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 1000 1100 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 1000 1100 | %PYTHON_ENV% evaluator.py

echo 1000 1100 1000
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 1100 1000 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 1100 1000 | %PYTHON_ENV% evaluator.py

echo 1000 1100 1100
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1000 1100 1100 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1000 1100 1100 | %PYTHON_ENV% evaluator.py

echo 1100 1000 1100
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1100 1000 1100 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1100 1000 1100 | %PYTHON_ENV% evaluator.py

echo 1100 1100 1000
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1100 1100 1000 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1100 1100 1000 | %PYTHON_ENV% evaluator.py

echo 1100 1100 1100
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1100 1100 1100 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1100 1100 1100 | %PYTHON_ENV% evaluator.py

echo 1200 1100 1100
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1200 1100 1100 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1200 1100 1100 | %PYTHON_ENV% evaluator.py

echo 1100 1200 1100
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1100 1200 1100 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1100 1200 1100 | %PYTHON_ENV% evaluator.py

echo 1100 1100 1200
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1100 1100 1200 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1100 1100 1200 | %PYTHON_ENV% evaluator.py

echo 1200 1200 1100
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1200 1200 1100 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1200 1200 1100 | %PYTHON_ENV% evaluator.py

echo 1200 1100 1200
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1200 1100 1200 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1200 1100 1200 | %PYTHON_ENV% evaluator.py

echo 1100 1200 1200
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1100 1200 1200 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1100 1200 1200 | %PYTHON_ENV% evaluator.py

echo 1200 1200 1200
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1200 1200 1200 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1200 1200 1200 | %PYTHON_ENV% evaluator.py

echo 1300 1200 1200
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1300 1200 1200 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1300 1200 1200 | %PYTHON_ENV% evaluator.py

echo 1200 1300 1200
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1200 1300 1200 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1200 1300 1200 | %PYTHON_ENV% evaluator.py

echo 1200 1200 1300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1200 1200 1300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1200 1200 1300 | %PYTHON_ENV% evaluator.py

echo 1300 1300 1200
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1300 1300 1200 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1300 1300 1200 | %PYTHON_ENV% evaluator.py

echo 1200 1300 1300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1200 1300 1300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1200 1300 1300 | %PYTHON_ENV% evaluator.py

echo 1300 1200 1300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1300 1200 1300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1300 1200 1300 | %PYTHON_ENV% evaluator.py

echo 1300 1300 1300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1300 1300 1300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1300 1300 1300 | %PYTHON_ENV% evaluator.py

echo 1400 1300 1300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1400 1300 1300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1400 1300 1300 | %PYTHON_ENV% evaluator.py

echo 1300 1400 1300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1300 1400 1300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1300 1400 1300 | %PYTHON_ENV% evaluator.py

echo 1300 1300 1400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1300 1300 1400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1300 1300 1400 | %PYTHON_ENV% evaluator.py

echo 1400 1400 1300
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1400 1400 1300 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1400 1400 1300 | %PYTHON_ENV% evaluator.py

echo 1300 1400 1400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1300 1400 1400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1300 1400 1400 | %PYTHON_ENV% evaluator.py

echo 1400 1300 1400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1400 1300 1400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1400 1300 1400 | %PYTHON_ENV% evaluator.py

echo 1400 1400 1400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1400 1400 1400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1400 1400 1400 | %PYTHON_ENV% evaluator.py

echo 1500 1400 1400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1500 1400 1400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1500 1400 1400 | %PYTHON_ENV% evaluator.py

echo 1400 1500 1400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1400 1500 1400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1400 1500 1400 | %PYTHON_ENV% evaluator.py

echo 1400 1400 1500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1400 1400 1500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1400 1400 1500 | %PYTHON_ENV% evaluator.py

echo 1500 1500 1400
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1500 1500 1400 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1500 1500 1400 | %PYTHON_ENV% evaluator.py

echo 1400 1500 1500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1400 1500 1500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1400 1500 1500 | %PYTHON_ENV% evaluator.py

echo 1500 1400 1500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1500 1400 1500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1500 1400 1500 | %PYTHON_ENV% evaluator.py

echo 1500 1500 1500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1500 1500 1500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1500 1500 1500 | %PYTHON_ENV% evaluator.py

echo 1600 1500 1500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1600 1500 1500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1600 1500 1500 | %PYTHON_ENV% evaluator.py

echo 1500 1600 1500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1500 1600 1500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1500 1600 1500 | %PYTHON_ENV% evaluator.py

echo 1500 1500 1600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1500 1500 1600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1500 1500 1600 | %PYTHON_ENV% evaluator.py

echo 1600 1600 1500
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1600 1600 1500 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1600 1600 1500 | %PYTHON_ENV% evaluator.py

echo 1500 1600 1600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1500 1600 1600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1500 1600 1600 | %PYTHON_ENV% evaluator.py

echo 1600 1500 1600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1600 1500 1600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1600 1500 1600 | %PYTHON_ENV% evaluator.py

echo 1600 1600 1600
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_1.log 1600 1600 1600 | %PYTHON_ENV% evaluator.py
java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App week_2.log 1600 1600 1600 | %PYTHON_ENV% evaluator.py
