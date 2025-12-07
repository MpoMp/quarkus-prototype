
# ADR-5: Code architecture

## Status

**Status:** Active

**Proposed by:** Michalis Bogdanos

**Authored by:** Michalis Bogdanos

## Context

_What is the issue that we're seeing that is motivating this decision or change?_ 

The code must be organized in a standard manner. 

## Decision

_What is the change that we're proposing and/or doing?_ 

We will go with the typical layered model. 
Namely; `endpoint`, `biz`, `persistence`, `integration`. 

Model (domain) classes (a.k.a. entities) shall be defined per layer to allow for loose coupling;
- Integration/Persistence layer shall use the entity name itself; e.g. `Car`
  - Unless an external entity is used in which case that one will be kept; e.g. `CarObj` from a third-party library
  - Persistence layer can optionally use `Record` as a suffix, to indicate a database record.
- Business layer shall use the DTO suffix; e.g. `CarDto`
- Endpoint/Presentation layer shall use the `Rsp` suffix (as in Response); e.g. `CarRsp`
  - For requests, the suffix shall be `Req`.

## Consequences

### Pros 

_List the advantages of the decision here._

- Using a standard that is well-adopted.
- Isolation of concerns. 
- Clear responsibilities per layer. 

### Cons

_List the disadvantages of the decision here._

- DTO and Mapper boilerplate code, especially when an entity is identical across layers.
