
# ADR-2: Application testing

## Status

**Status:** Active

**Proposed by:** Michalis Bogdanos

**Authored by:** Michalis Bogdanos

## Context

_What is the issue that we're seeing that is motivating this decision or change?_ 

Guidelines and tooling to be used for unit and integration testing.

Integration tests in Quarkus fire up a separate execution JVM for the test itself. The test invokes the application's 
exposed API. This is also the only way to test the native image.

## Decision

_What is the change that we're proposing and/or doing?_ 

`quarkus-rest` brings in the `rest-assured` library so we will use [that](https://rest-assured.io/) for testing the 
REST-like endpoints. 

JUnit 5, as brought in by the Quarkus platform.

We will follow the paradigm of the Maven tooling for testing; using the `Test` and `IT` suffix for test classes.

## Consequences

### Pros 

_List the advantages of the decision here._

- Standard way of testing the REST-like endpoints.

### Cons

_List the disadvantages of the decision here._

- Whatever disadvantages the frameworks and tools themselves bring.
