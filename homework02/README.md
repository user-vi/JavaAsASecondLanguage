# Dependency Injection Framework

## Wat?
General info about [DI](https://en.wikipedia.org/wiki/Dependency_injection).

For inspiration [DI in Spring Framework](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring).

## Our project
We have a multi-module gradle project:
- `:webserver` - a simple web server we found somewhere and slightly modified
- `:di` - a library module implementing Dependency Injection Framework.

## Your task
1. Implement :di module, to run Webserver
    1. Support Type, Interface and Superclass based injections
        - Integer <- inject(Integer.class)
        - Number  <- inject(Integer.class)
        - List <- inject(ArrayList.class) 
    1. Think about cases:
        - Where multiple candidates are available to inject.
        - There is no right candidate to inject.
1. Pass `WebServerEnd2EndTest`
1. Write JUnit tests for both :di and :webserver modules
1. Calculate JUnit test coverage
1. Implement Logger with different message severities(you are free to use libraries) 
and configure it with DI. 

## Score
This task has a high level of uncertainty. Make decisions based on your research and discretion. 
You are free to discuss architecture and implementation ideas with other students publicly.

We are going to rank solutions based on:
- completeness (DI is usable)
- correctness (DI and webserver are well tested with most corner cases covered) 
- code quality (Code is well written end easy to understand)

**Max score: 4 points.** 

## Build and test
1. Use `shadowJar` task to build `:webserver` fat jar
1. Use `:webserver:check` to run `:webserver` tests 
1. Use `:di:check` to run `:di` tests 
 