
# ADR-7: Test naming

## Status

**Status:** Active

**Proposed by:** Michalis Bogdanos

**Authored by:** Michalis Bogdanos

## Context

_What is the issue that we're seeing that is motivating this decision or change?_ 

A standard way of naming test methods.

## Decision

_What is the change that we're proposing and/or doing?_ 

Test methods shall be named according to the following pattern:
- The prefix is the name of the method under test.
- The suffix is the number of the test case.

All other information like the test description, the kind of test data, the expected result, etc. shall be placed in 
the appropriate annotations or Javadoc. 

The default number format is using two digits; i.e. starting from 01. 
More digits can be added if needed.

## Consequences

### Pros 

_List the advantages of the decision here._

- Consistent naming convention for test methods.
- Improved readability and maintainability of test code.
- No "rail-road" method names that are hard to read.

### Cons

_List the disadvantages of the decision here._

- Simpler tests will still need to have a description annotation or Javadoc.
