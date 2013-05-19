
Solution for the Prezi Scale Contest
====================================

The solution is based on Java 7, so JDK 1.7 needs to be installed to run the solution properly.

Download the repository as a zip file, decompress it and compile it with the following command from the main directory:

	javac -classpath .;lib/commons-math3-3.2.jar -d target/classes -sourcepath src/main/java src/main/java/com/acme/challenge/App.java

After the source is compiled, it can be run and evaluated with the following command (path of the input log is given as a program argument):
	java -cp target/classes;lib/commons-math3-3.2.jar com.acme.challenge.App {log-path} | evaluator.py



Brief summary of the solution:
------------------------------

1.	The system's (that is seen as a black box) inner behavior is simulated. It means that simulated machines are created, and the incoming jobs are distributed among these machines in the same manner as it's happening in the real system. This gives an estimation about the number of the machines needed in a given second.

2.	This estimation is computed in every second, and is stored in some data structure.

3.	Based on the estimations of a previous timeframe (~6-8 mins) a prediction is made (with simple regression) about the future 2 minutes from the current second. It is needed because of the booting time of a machine.

4. Based on the prediction, VMs are launched or nominated to termination (not terminated instantly only if near billing time).

5. Launching time of new machines are logged, so it can be decided if it is worth making a termination. A machine is terminated only if it's near its billing time. This termination timeframe is based on the average job length of a queue.

6. Extreme peaks that take just a few seconds cannot be predicted but needs to be handled. These peaks can be very extreme sometimes, so it is necessary to have much more VMs launched than the prediction in every moment. These rates are determined hourly and are computed from the simulations of the 2 weeks. These computations can be found in [this repo](https://github.com/doktoric/help-my-challenge-excel-exporter)