Insight Data Engineering Code Summary:

List of dependencies(located in lib):
1. hamcrest-core-1.3.jar - required for running JUNiT unit tests
    (http://central.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar)
2. junit-4.12.jar - required for running JUNIT unit tests
    (http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar)
3. gson-2.6.2.jar - (optional) - JsonPaymentParser class uses this library for parsing the payments.txt


Summary of Packages added:
1. src/lib - comprises the jars, required for parsing payments and running junit test.
2. scr/launch - comprises the Executor class for launching the program.
3. src/parser - comprises of classes which implement the parser functionality for parsing the text into Payment instances.
4. src/payment - comprises of Payment class to represent the json Payment text.
5. src/validation - comprises of ValidationTimeStampPayment to validate payment falls with the designated 60 second window.
6. src/graph - comprises of VenoGraph which represents the graph built from the payments along with MedianFinder class to compute median.
7. src/stream - comprises of Reader/Writer class to perform operations of reading from file/writing to file.
8. src/unittest - comprises of JUNIT unittests for each of the classes added.
9. doc - javaDoc for all the classes

Running tests:
Sample output
38c986177001:insight_testsuite vnagarajan$ pwd
/Users/vnagarajan/Documents/Insight/insight_testsuite

38c986177001:insight_testsuite vnagarajan$ ./run_tests.sh
[PASS]: test-1-venmo-trans
[PASS]: test-2-venmo-trans
[PASS]: test-3-venmo-trans