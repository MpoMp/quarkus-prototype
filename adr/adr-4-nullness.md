
# ADR-4: Nullness annotations

## Status

**Status:** Active

**Proposed by:** Michalis Bogdanos

**Authored by:** Michalis Bogdanos

## Context

_What is the issue that we're seeing that is motivating this decision or change?_ 

A standard for annotating Java code for nullness. 

## Decision

_What is the change that we're proposing and/or doing?_ 

We'll use https://jspecify.dev/. 
The guideline is to annotate on class level with `@NullMarked`. 
Do not annotate on package or module level. 

This allows for a good middle ground between keeping the annotation at a too-high level and a too-low level. 
It is visible to new and old readers of the code while they browse, without having to inspect package/module files. 


## Consequences

### Pros 

_List the advantages of the decision here._

- Using a standard that is well-adopted.
- Reduces boilerplate code by annotating at class level.

### Cons

_List the disadvantages of the decision here._

- An extra Maven dependency; but a loghtweight one.
