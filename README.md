# Automation Example Project

A sample project showcasing Selenium and Rest Assured based QA automation.

The following demo sites are used:
* http://automationpractice.com - UI
* https://jsonplaceholder.typicode.com/users - API

## Prerequisites

* JDK 8
* Maven 3

## Usage Guide

Execute the following to run both framework unit tests and QA integration tests.
> mvn clean verify

Execute the following to run only the framework unit tests.
> mvn clean test

Logs can be found in the following locations:
* logs/integration.log - messages generated while running the QA integration tests.
* logs/unit.log - messages generated while running the framework's unit tests.