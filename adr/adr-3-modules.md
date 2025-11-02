
# ADR-3: Module design

## Status

**Status:** Active

**Proposed by:** Michalis Bogdanos

**Authored by:** Michalis Bogdanos

## Context

_What is the issue that we're seeing that is motivating this decision or change?_ 

A need for having multiple services live under the same repository. 
Library modules will also live in here.

## Decision

_What is the change that we're proposing and/or doing?_ 

All applications and libraries will be implemented within their own Maven project.

## Consequences

### Pros 

_List the advantages of the decision here._

- A single repository.
- Each module is free to use its own extensions.
- We can host multiple applications in the same repository.

### Cons

_List the disadvantages of the decision here._

- Much duplication. 
