# Contour QA Automation Assignment

This is the implementation for Contour QA automation test using REST Assured, Cucumber, TestNG and Maven

There is 1 cucumber feature which represent an end-to-end test case of Profile Manager application. In case you want to change the input value, you can update the value to any positive Integer number

To run this feature, please add `tags = {"@api.profilemanagement"}` in **TestRunner.java**

The automation runs in **API level**

## To run the code:
- Using IntelliJ IDEA: Clone the project and import to IntelliJ. Make sure your Project bytecode version is 11 in the Java Compiler setting. You may also need to install Cucumber for Java plugin. Run TestRunner.java as TestNG class

- Using command line: Make sure you have Maven installed (run mvn --version to check). In terminal, clone and cd to the project directory, then run `mvn clean test -Dtest=TestRunner`